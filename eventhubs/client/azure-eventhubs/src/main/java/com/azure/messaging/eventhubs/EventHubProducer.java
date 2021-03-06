// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.messaging.eventhubs;

import com.azure.core.amqp.exception.AmqpException;
import com.azure.core.amqp.exception.ErrorCondition;
import com.azure.core.implementation.util.ImplUtils;
import com.azure.core.util.logging.ClientLogger;
import com.azure.messaging.eventhubs.implementation.AmqpSendLink;
import com.azure.messaging.eventhubs.implementation.ErrorContextProvider;
import com.azure.messaging.eventhubs.implementation.EventDataUtil;
import org.apache.qpid.proton.message.Message;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * A producer responsible for transmitting {@link EventData} to a specific Event Hub, grouped together in batches.
 * Depending on the options specified at creation, the producer may be created to allow event data to be automatically
 * routed to an available partition or specific to a partition.
 *
 * <p>
 * Allowing automatic routing of partitions is recommended when:
 * <ul>
 * <li>The sending of events needs to be highly available.</li>
 * <li>The event data should be evenly distributed among all available partitions.</li>
 * </ul>
 * </p>
 *
 * <p>
 * If no partition is specified, the following rules are used for automatically selecting one:
 * <ol>
 * <li>Distribute the events equally amongst all available partitions using a round-robin approach.</li>
 * <li>If a partition becomes unavailable, the Event Hubs service will automatically detect it and forward the
 * message to another available partition.</li>
 * </ol>
 * </p>
 *
 * @see EventHubClient#createProducer()
 */
public class EventHubProducer implements Closeable {
    /**
     * The default maximum allowable size, in bytes, for a batch to be sent.
     */
    public static final int MAX_MESSAGE_LENGTH_BYTES = 256 * 1024;

    private static final int MAX_PARTITION_KEY_LENGTH = 128;
    private static final SendOptions DEFAULT_SEND_OPTIONS = new SendOptions();

    private final ClientLogger logger = new ClientLogger(EventHubProducer.class);
    private final AtomicBoolean isDisposed = new AtomicBoolean();
    private final EventHubProducerOptions senderOptions;
    private final Mono<AmqpSendLink> sendLinkMono;
    private final boolean isPartitionSender;

    /**
     * Creates a new instance of this {@link EventHubProducer} that sends messages to {@link
     * EventHubProducerOptions#partitionId() options.partitionId()} if it is not {@code null} or an empty string,
     * otherwise, allows the service to load balance the messages amongst available partitions.
     */
    EventHubProducer(Mono<AmqpSendLink> amqpSendLinkMono, EventHubProducerOptions options) {
        // Caching the created link so we don't invoke another link creation.
        this.sendLinkMono = amqpSendLinkMono.cache();
        this.senderOptions = options;
        this.isPartitionSender = !ImplUtils.isNullOrEmpty(options.partitionId());
    }

    /**
     * Sends a single event to the associated Event Hub. If the size of the single event exceeds the maximum size
     * allowed, an exception will be triggered and the send will fail.
     *
     * For more information regarding the maximum event size allowed, see
     * <a href="https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-quotas">Azure Event Hubs Quotas and
     * Limits</a>.
     *
     * @param event Event to send to the service.
     * @return A {@link Mono} that completes when the event is pushed to the service.
     */
    public Mono<Void> send(EventData event) {
        Objects.requireNonNull(event);

        return send(Flux.just(event));
    }

    /**
     * Sends a single event to the associated Event Hub with the send options. If the size of the single event exceeds
     * the maximum size allowed, an exception will be triggered and the send will fail.
     *
     * For more information regarding the maximum event size allowed, see
     * <a href="https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-quotas">Azure Event Hubs Quotas and
     * Limits</a>.
     *
     * @param event Event to send to the service.
     * @param options The set of options to consider when sending this event.
     * @return A {@link Mono} that completes when the event is pushed to the service.
     */
    public Mono<Void> send(EventData event, SendOptions options) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(options);

        return send(Flux.just(event), options);
    }

    /**
     * Sends a set of events to the associated Event Hub using a batched approach. If the size of events exceed the
     * maximum size of a single batch, an exception will be triggered and the send will fail. By default, the message
     * size is the max amount allowed on the link.
     *
     * @param events Events to send to the service.
     * @return A {@link Mono} that completes when all events are pushed to the service.
     */
    public Mono<Void> send(Iterable<EventData> events) {
        Objects.requireNonNull(events);

        return send(Flux.fromIterable(events));
    }

    /**
     * Sends a set of events to the associated Event Hub using a batched approach. If the size of events exceed the
     * maximum size of a single batch, an exception will be triggered and the send will fail. By default, the message
     * size is the max amount allowed on the link.
     *
     * @param events Events to send to the service.
     * @param options The set of options to consider when sending this batch.
     * @return A {@link Mono} that completes when all events are pushed to the service.
     */
    public Mono<Void> send(Iterable<EventData> events, SendOptions options) {
        Objects.requireNonNull(events);

        return send(Flux.fromIterable(events), options);
    }

    /**
     * Sends a set of events to the associated Event Hub using a batched approach. If the size of events exceed the
     * maximum size of a single batch, an exception will be triggered and the send will fail. By default, the message
     * size is the max amount allowed on the link.
     *
     * @param events Events to send to the service.
     * @return A {@link Mono} that completes when all events are pushed to the service.
     */
    public Mono<Void> send(Publisher<EventData> events) {
        Objects.requireNonNull(events);

        return sendInternal(Flux.from(events), DEFAULT_SEND_OPTIONS);
    }

    /**
     * Sends a set of events to the associated Event Hub using a batched approach. If the size of events exceed the
     * maximum size of a single batch, an exception will be triggered and the send will fail. By default, the message
     * size is the max amount allowed on the link.
     *
     * @param events Events to send to the service.
     * @param options The set of options to consider when sending this batch.
     * @return A {@link Mono} that completes when all events are pushed to the service.
     */
    public Mono<Void> send(Publisher<EventData> events, SendOptions options) {
        Objects.requireNonNull(events);
        Objects.requireNonNull(options);

        return sendInternal(Flux.from(events), options);
    }

    private Mono<Void> sendInternal(Flux<EventData> events, SendOptions options) {
        final String partitionKey = options.partitionKey();

        if (!ImplUtils.isNullOrEmpty(partitionKey)) {
            if (isPartitionSender) {
                throw new IllegalArgumentException(String.format(Locale.US,
                    "SendOptions.partitionKey() cannot be set when an EventHubProducer is created with"
                        + "EventHubProducerOptions.partitionId() set. This EventHubProducer can only send events to partition '%s'.",
                    senderOptions.partitionId()));
            } else if (partitionKey.length() > MAX_PARTITION_KEY_LENGTH) {
                throw new IllegalArgumentException(String.format(Locale.US,
                    "PartitionKey '%s' exceeds the maximum allowed length: '%s'.", partitionKey, MAX_PARTITION_KEY_LENGTH));
            }
        }

        //TODO (conniey): When we implement partial success, update the maximum number of batches or remove it completely.
        return sendLinkMono.flatMap(link -> {
            return events.collect(new EventDataCollector(options, 1, link::getErrorContext))
                .flatMap(list -> send(Flux.fromIterable(list)));
        });
    }

    private Mono<Void> send(Flux<EventDataBatch> eventBatches) {
        return eventBatches
            .flatMap(this::send)
            .then()
            .doOnError(error -> {
                logger.asError().log("Error sending batch.", error);
            });
    }

    private Mono<Void> send(EventDataBatch batch) {
        if (batch.getEvents().isEmpty()) {
            logger.asInfo().log("Cannot send an EventBatch that is empty.");
            return Mono.empty();
        }

        logger.asInfo().log("Sending batch with partitionKey[{}], size[{}].", batch.getPartitionKey(), batch.getSize());

        final List<Message> messages = EventDataUtil.toAmqpMessage(batch.getPartitionKey(), batch.getEvents());

        return sendLinkMono.flatMap(link -> messages.size() == 1
            ? link.send(messages.get(0))
            : link.send(messages));
    }

    /**
     * Disposes of the {@link EventHubProducer} by closing the underlying connection to the service.
     *
     * @throws IOException if the underlying transport could not be closed and its resources could not be
     *         disposed.
     */
    @Override
    public void close() throws IOException {
        if (!isDisposed.getAndSet(true)) {
            final AmqpSendLink block = sendLinkMono.block(senderOptions.timeout());
            if (block != null) {
                block.close();
            }
        }
    }

    /**
     * Collects EventData into EventDataBatch to send to Event Hubs. If {@code maxNumberOfBatches} is {@code null} then
     * it'll collect as many batches as possible. Otherwise, if there are more events than can fit into {@code
     * maxNumberOfBatches}, then the collector throws a {@link AmqpException} with {@link
     * ErrorCondition#LINK_PAYLOAD_SIZE_EXCEEDED}.
     */
    private static class EventDataCollector implements Collector<EventData, List<EventDataBatch>, List<EventDataBatch>> {
        private final String partitionKey;
        private final int maxMessageSize;
        private final Integer maxNumberOfBatches;
        private final ErrorContextProvider contextProvider;

        private volatile EventDataBatch currentBatch;

        EventDataCollector(SendOptions options, Integer maxNumberOfBatches, ErrorContextProvider contextProvider) {
            this.maxNumberOfBatches = maxNumberOfBatches;
            this.maxMessageSize = options.maximumSizeInBytes();
            this.partitionKey = options.partitionKey();
            this.contextProvider = contextProvider;

            currentBatch = new EventDataBatch(options.maximumSizeInBytes(), options.partitionKey(), contextProvider);
        }

        @Override
        public Supplier<List<EventDataBatch>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<EventDataBatch>, EventData> accumulator() {
            return (list, event) -> {
                EventDataBatch batch = currentBatch;
                if (batch.tryAdd(event)) {
                    return;
                }

                if (maxNumberOfBatches != null && list.size() == maxNumberOfBatches) {
                    final String message = String.format(Locale.US,
                        "EventData does not fit into maximum number of batches. '%s'", maxNumberOfBatches);

                    throw new AmqpException(false, ErrorCondition.LINK_PAYLOAD_SIZE_EXCEEDED, message, contextProvider.getErrorContext());
                }

                currentBatch = new EventDataBatch(maxMessageSize, partitionKey, contextProvider);
                currentBatch.tryAdd(event);
                list.add(batch);
            };
        }

        @Override
        public BinaryOperator<List<EventDataBatch>> combiner() {
            return (existing, another) -> {
                existing.addAll(another);
                return existing;
            };
        }

        @Override
        public Function<List<EventDataBatch>, List<EventDataBatch>> finisher() {
            return list -> {
                EventDataBatch batch = currentBatch;
                currentBatch = null;

                if (batch != null) {
                    list.add(batch);
                }

                return list;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }
}
