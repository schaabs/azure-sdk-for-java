/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.language.textanalytics.implementation;

import com.microsoft.azure.cognitiveservices.language.textanalytics.models.EntitiesOptionalParameter;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.SentimentOptionalParameter;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.DetectLanguageOptionalParameter;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.KeyPhrasesOptionalParameter;
import retrofit2.Retrofit;
import com.microsoft.azure.cognitiveservices.language.textanalytics.TextAnalytics;
import com.google.common.base.Joiner;
import com.google.common.reflect.TypeToken;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.BatchInput;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.EntitiesBatchResult;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.ErrorResponseException;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.Input;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.KeyPhraseBatchResult;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.LanguageBatchResult;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.MultiLanguageBatchInput;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.MultiLanguageInput;
import com.microsoft.azure.cognitiveservices.language.textanalytics.models.SentimentBatchResult;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import com.microsoft.rest.Validator;
import java.io.IOException;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in TextAnalytics.
 */
public class TextAnalyticsImpl implements TextAnalytics {
    /** The Retrofit service to perform REST calls. */
    private TextAnalyticsService service;
    /** The service client containing this operation class. */
    private TextAnalyticsAPIImpl client;

    /**
     * Initializes an instance of TextAnalyticsImpl.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    public TextAnalyticsImpl(Retrofit retrofit, TextAnalyticsAPIImpl client) {
        this.service = retrofit.create(TextAnalyticsService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for TextAnalytics to be
     * used by Retrofit to perform actually REST calls.
     */
    interface TextAnalyticsService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.cognitiveservices.language.textanalytics.TextAnalytics entities" })
        @POST("v2.0/entities")
        Observable<Response<ResponseBody>> entities(@Header("accept-language") String acceptLanguage, @Body MultiLanguageBatchInput input, @Header("x-ms-parameterized-host") String parameterizedHost, @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.cognitiveservices.language.textanalytics.TextAnalytics sentiment" })
        @POST("v2.0/sentiment")
        Observable<Response<ResponseBody>> sentiment(@Header("accept-language") String acceptLanguage, @Body MultiLanguageBatchInput input, @Header("x-ms-parameterized-host") String parameterizedHost, @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.cognitiveservices.language.textanalytics.TextAnalytics detectLanguage" })
        @POST("v2.0/languages")
        Observable<Response<ResponseBody>> detectLanguage(@Header("accept-language") String acceptLanguage, @Body BatchInput input, @Header("x-ms-parameterized-host") String parameterizedHost, @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.cognitiveservices.language.textanalytics.TextAnalytics keyPhrases" })
        @POST("v2.0/keyPhrases")
        Observable<Response<ResponseBody>> keyPhrases(@Header("accept-language") String acceptLanguage, @Body MultiLanguageBatchInput input, @Header("x-ms-parameterized-host") String parameterizedHost, @Header("User-Agent") String userAgent);

    }


    /**
     * The API returns a list of recognized entities in a given document.
     * To get even more information on each recognized entity we recommend using the Bing Entity Search API by querying for the recognized entities names. See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/text-analytics-supported-languages"&gt;Supported languages in Text Analytics API&lt;/a&gt; for the list of enabled languages.
     *
     * @param entitiesOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the EntitiesBatchResult object if successful.
     */
    public EntitiesBatchResult entities(EntitiesOptionalParameter entitiesOptionalParameter) {
        return entitiesWithServiceResponseAsync(entitiesOptionalParameter).toBlocking().single().body();
    }

    /**
     * The API returns a list of recognized entities in a given document.
     * To get even more information on each recognized entity we recommend using the Bing Entity Search API by querying for the recognized entities names. See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/text-analytics-supported-languages"&gt;Supported languages in Text Analytics API&lt;/a&gt; for the list of enabled languages.
     *
     * @param entitiesOptionalParameter the object representing the optional parameters to be set before calling this API
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<EntitiesBatchResult> entitiesAsync(EntitiesOptionalParameter entitiesOptionalParameter, final ServiceCallback<EntitiesBatchResult> serviceCallback) {
        return ServiceFuture.fromResponse(entitiesWithServiceResponseAsync(entitiesOptionalParameter), serviceCallback);
    }

    /**
     * The API returns a list of recognized entities in a given document.
     * To get even more information on each recognized entity we recommend using the Bing Entity Search API by querying for the recognized entities names. See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/text-analytics-supported-languages"&gt;Supported languages in Text Analytics API&lt;/a&gt; for the list of enabled languages.
     *
     * @param entitiesOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EntitiesBatchResult object
     */
    public Observable<EntitiesBatchResult> entitiesAsync(EntitiesOptionalParameter entitiesOptionalParameter) {
        return entitiesWithServiceResponseAsync(entitiesOptionalParameter).map(new Func1<ServiceResponse<EntitiesBatchResult>, EntitiesBatchResult>() {
            @Override
            public EntitiesBatchResult call(ServiceResponse<EntitiesBatchResult> response) {
                return response.body();
            }
        });
    }

    /**
     * The API returns a list of recognized entities in a given document.
     * To get even more information on each recognized entity we recommend using the Bing Entity Search API by querying for the recognized entities names. See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/text-analytics-supported-languages"&gt;Supported languages in Text Analytics API&lt;/a&gt; for the list of enabled languages.
     *
     * @param entitiesOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EntitiesBatchResult object
     */
    public Observable<ServiceResponse<EntitiesBatchResult>> entitiesWithServiceResponseAsync(EntitiesOptionalParameter entitiesOptionalParameter) {
        if (this.client.azureRegion() == null) {
            throw new IllegalArgumentException("Parameter this.client.azureRegion() is required and cannot be null.");
        }
        final List<MultiLanguageInput> documents = entitiesOptionalParameter != null ? entitiesOptionalParameter.documents() : null;

        return entitiesWithServiceResponseAsync(documents);
    }

    /**
     * The API returns a list of recognized entities in a given document.
     * To get even more information on each recognized entity we recommend using the Bing Entity Search API by querying for the recognized entities names. See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/text-analytics-supported-languages"&gt;Supported languages in Text Analytics API&lt;/a&gt; for the list of enabled languages.
     *
     * @param documents the List&lt;MultiLanguageInput&gt; value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EntitiesBatchResult object
     */
    public Observable<ServiceResponse<EntitiesBatchResult>> entitiesWithServiceResponseAsync(List<MultiLanguageInput> documents) {
        if (this.client.azureRegion() == null) {
            throw new IllegalArgumentException("Parameter this.client.azureRegion() is required and cannot be null.");
        }
        Validator.validate(documents);
        MultiLanguageBatchInput input = new MultiLanguageBatchInput();
        input.withDocuments(documents);
        String parameterizedHost = Joiner.on(", ").join("{AzureRegion}", this.client.azureRegion());
        return service.entities(this.client.acceptLanguage(), input, parameterizedHost, this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<EntitiesBatchResult>>>() {
                @Override
                public Observable<ServiceResponse<EntitiesBatchResult>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<EntitiesBatchResult> clientResponse = entitiesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<EntitiesBatchResult> entitiesDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<EntitiesBatchResult, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<EntitiesBatchResult>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    @Override
    public TextAnalyticsEntitiesParameters entities() {
        return new TextAnalyticsEntitiesParameters(this);
    }

    /**
     * Internal class implementing TextAnalyticsEntitiesDefinition.
     */
    class TextAnalyticsEntitiesParameters implements TextAnalyticsEntitiesDefinition {
        private TextAnalyticsImpl parent;
        private List<MultiLanguageInput> documents;

        /**
         * Constructor.
         * @param parent the parent object.
         */
        TextAnalyticsEntitiesParameters(TextAnalyticsImpl parent) {
            this.parent = parent;
        }

        @Override
        public TextAnalyticsEntitiesParameters withDocuments(List<MultiLanguageInput> documents) {
            this.documents = documents;
            return this;
        }

        @Override
        public EntitiesBatchResult execute() {
        return entitiesWithServiceResponseAsync(documents).toBlocking().single().body();
    }

        @Override
        public Observable<EntitiesBatchResult> executeAsync() {
            return entitiesWithServiceResponseAsync(documents).map(new Func1<ServiceResponse<EntitiesBatchResult>, EntitiesBatchResult>() {
                @Override
                public EntitiesBatchResult call(ServiceResponse<EntitiesBatchResult> response) {
                    return response.body();
                }
            });
        }
    }


    /**
     * The API returns a numeric score between 0 and 1.
     * Scores close to 1 indicate positive sentiment, while scores close to 0 indicate negative sentiment. A score of 0.5 indicates the lack of sentiment (e.g. a factoid statement). See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by sentiment analysis.
     *
     * @param sentimentOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the SentimentBatchResult object if successful.
     */
    public SentimentBatchResult sentiment(SentimentOptionalParameter sentimentOptionalParameter) {
        return sentimentWithServiceResponseAsync(sentimentOptionalParameter).toBlocking().single().body();
    }

    /**
     * The API returns a numeric score between 0 and 1.
     * Scores close to 1 indicate positive sentiment, while scores close to 0 indicate negative sentiment. A score of 0.5 indicates the lack of sentiment (e.g. a factoid statement). See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by sentiment analysis.
     *
     * @param sentimentOptionalParameter the object representing the optional parameters to be set before calling this API
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<SentimentBatchResult> sentimentAsync(SentimentOptionalParameter sentimentOptionalParameter, final ServiceCallback<SentimentBatchResult> serviceCallback) {
        return ServiceFuture.fromResponse(sentimentWithServiceResponseAsync(sentimentOptionalParameter), serviceCallback);
    }

    /**
     * The API returns a numeric score between 0 and 1.
     * Scores close to 1 indicate positive sentiment, while scores close to 0 indicate negative sentiment. A score of 0.5 indicates the lack of sentiment (e.g. a factoid statement). See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by sentiment analysis.
     *
     * @param sentimentOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the SentimentBatchResult object
     */
    public Observable<SentimentBatchResult> sentimentAsync(SentimentOptionalParameter sentimentOptionalParameter) {
        return sentimentWithServiceResponseAsync(sentimentOptionalParameter).map(new Func1<ServiceResponse<SentimentBatchResult>, SentimentBatchResult>() {
            @Override
            public SentimentBatchResult call(ServiceResponse<SentimentBatchResult> response) {
                return response.body();
            }
        });
    }

    /**
     * The API returns a numeric score between 0 and 1.
     * Scores close to 1 indicate positive sentiment, while scores close to 0 indicate negative sentiment. A score of 0.5 indicates the lack of sentiment (e.g. a factoid statement). See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by sentiment analysis.
     *
     * @param sentimentOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the SentimentBatchResult object
     */
    public Observable<ServiceResponse<SentimentBatchResult>> sentimentWithServiceResponseAsync(SentimentOptionalParameter sentimentOptionalParameter) {
        if (this.client.azureRegion() == null) {
            throw new IllegalArgumentException("Parameter this.client.azureRegion() is required and cannot be null.");
        }
        final List<MultiLanguageInput> documents = sentimentOptionalParameter != null ? sentimentOptionalParameter.documents() : null;

        return sentimentWithServiceResponseAsync(documents);
    }

    /**
     * The API returns a numeric score between 0 and 1.
     * Scores close to 1 indicate positive sentiment, while scores close to 0 indicate negative sentiment. A score of 0.5 indicates the lack of sentiment (e.g. a factoid statement). See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by sentiment analysis.
     *
     * @param documents the List&lt;MultiLanguageInput&gt; value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the SentimentBatchResult object
     */
    public Observable<ServiceResponse<SentimentBatchResult>> sentimentWithServiceResponseAsync(List<MultiLanguageInput> documents) {
        if (this.client.azureRegion() == null) {
            throw new IllegalArgumentException("Parameter this.client.azureRegion() is required and cannot be null.");
        }
        Validator.validate(documents);
        MultiLanguageBatchInput input = new MultiLanguageBatchInput();
        input.withDocuments(documents);
        String parameterizedHost = Joiner.on(", ").join("{AzureRegion}", this.client.azureRegion());
        return service.sentiment(this.client.acceptLanguage(), input, parameterizedHost, this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<SentimentBatchResult>>>() {
                @Override
                public Observable<ServiceResponse<SentimentBatchResult>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<SentimentBatchResult> clientResponse = sentimentDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<SentimentBatchResult> sentimentDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<SentimentBatchResult, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<SentimentBatchResult>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    @Override
    public TextAnalyticsSentimentParameters sentiment() {
        return new TextAnalyticsSentimentParameters(this);
    }

    /**
     * Internal class implementing TextAnalyticsSentimentDefinition.
     */
    class TextAnalyticsSentimentParameters implements TextAnalyticsSentimentDefinition {
        private TextAnalyticsImpl parent;
        private List<MultiLanguageInput> documents;

        /**
         * Constructor.
         * @param parent the parent object.
         */
        TextAnalyticsSentimentParameters(TextAnalyticsImpl parent) {
            this.parent = parent;
        }

        @Override
        public TextAnalyticsSentimentParameters withDocuments(List<MultiLanguageInput> documents) {
            this.documents = documents;
            return this;
        }

        @Override
        public SentimentBatchResult execute() {
        return sentimentWithServiceResponseAsync(documents).toBlocking().single().body();
    }

        @Override
        public Observable<SentimentBatchResult> executeAsync() {
            return sentimentWithServiceResponseAsync(documents).map(new Func1<ServiceResponse<SentimentBatchResult>, SentimentBatchResult>() {
                @Override
                public SentimentBatchResult call(ServiceResponse<SentimentBatchResult> response) {
                    return response.body();
                }
            });
        }
    }


    /**
     * The API returns the detected language and a numeric score between 0 and 1.
     * Scores close to 1 indicate 100% certainty that the identified language is true. A total of 120 languages are supported.
     *
     * @param detectLanguageOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the LanguageBatchResult object if successful.
     */
    public LanguageBatchResult detectLanguage(DetectLanguageOptionalParameter detectLanguageOptionalParameter) {
        return detectLanguageWithServiceResponseAsync(detectLanguageOptionalParameter).toBlocking().single().body();
    }

    /**
     * The API returns the detected language and a numeric score between 0 and 1.
     * Scores close to 1 indicate 100% certainty that the identified language is true. A total of 120 languages are supported.
     *
     * @param detectLanguageOptionalParameter the object representing the optional parameters to be set before calling this API
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<LanguageBatchResult> detectLanguageAsync(DetectLanguageOptionalParameter detectLanguageOptionalParameter, final ServiceCallback<LanguageBatchResult> serviceCallback) {
        return ServiceFuture.fromResponse(detectLanguageWithServiceResponseAsync(detectLanguageOptionalParameter), serviceCallback);
    }

    /**
     * The API returns the detected language and a numeric score between 0 and 1.
     * Scores close to 1 indicate 100% certainty that the identified language is true. A total of 120 languages are supported.
     *
     * @param detectLanguageOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the LanguageBatchResult object
     */
    public Observable<LanguageBatchResult> detectLanguageAsync(DetectLanguageOptionalParameter detectLanguageOptionalParameter) {
        return detectLanguageWithServiceResponseAsync(detectLanguageOptionalParameter).map(new Func1<ServiceResponse<LanguageBatchResult>, LanguageBatchResult>() {
            @Override
            public LanguageBatchResult call(ServiceResponse<LanguageBatchResult> response) {
                return response.body();
            }
        });
    }

    /**
     * The API returns the detected language and a numeric score between 0 and 1.
     * Scores close to 1 indicate 100% certainty that the identified language is true. A total of 120 languages are supported.
     *
     * @param detectLanguageOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the LanguageBatchResult object
     */
    public Observable<ServiceResponse<LanguageBatchResult>> detectLanguageWithServiceResponseAsync(DetectLanguageOptionalParameter detectLanguageOptionalParameter) {
        if (this.client.azureRegion() == null) {
            throw new IllegalArgumentException("Parameter this.client.azureRegion() is required and cannot be null.");
        }
        final List<Input> documents = detectLanguageOptionalParameter != null ? detectLanguageOptionalParameter.documents() : null;

        return detectLanguageWithServiceResponseAsync(documents);
    }

    /**
     * The API returns the detected language and a numeric score between 0 and 1.
     * Scores close to 1 indicate 100% certainty that the identified language is true. A total of 120 languages are supported.
     *
     * @param documents the List&lt;Input&gt; value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the LanguageBatchResult object
     */
    public Observable<ServiceResponse<LanguageBatchResult>> detectLanguageWithServiceResponseAsync(List<Input> documents) {
        if (this.client.azureRegion() == null) {
            throw new IllegalArgumentException("Parameter this.client.azureRegion() is required and cannot be null.");
        }
        Validator.validate(documents);
        BatchInput input = new BatchInput();
        input.withDocuments(documents);
        String parameterizedHost = Joiner.on(", ").join("{AzureRegion}", this.client.azureRegion());
        return service.detectLanguage(this.client.acceptLanguage(), input, parameterizedHost, this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<LanguageBatchResult>>>() {
                @Override
                public Observable<ServiceResponse<LanguageBatchResult>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<LanguageBatchResult> clientResponse = detectLanguageDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<LanguageBatchResult> detectLanguageDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<LanguageBatchResult, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<LanguageBatchResult>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    @Override
    public TextAnalyticsDetectLanguageParameters detectLanguage() {
        return new TextAnalyticsDetectLanguageParameters(this);
    }

    /**
     * Internal class implementing TextAnalyticsDetectLanguageDefinition.
     */
    class TextAnalyticsDetectLanguageParameters implements TextAnalyticsDetectLanguageDefinition {
        private TextAnalyticsImpl parent;
        private List<Input> documents;

        /**
         * Constructor.
         * @param parent the parent object.
         */
        TextAnalyticsDetectLanguageParameters(TextAnalyticsImpl parent) {
            this.parent = parent;
        }

        @Override
        public TextAnalyticsDetectLanguageParameters withDocuments(List<Input> documents) {
            this.documents = documents;
            return this;
        }

        @Override
        public LanguageBatchResult execute() {
        return detectLanguageWithServiceResponseAsync(documents).toBlocking().single().body();
    }

        @Override
        public Observable<LanguageBatchResult> executeAsync() {
            return detectLanguageWithServiceResponseAsync(documents).map(new Func1<ServiceResponse<LanguageBatchResult>, LanguageBatchResult>() {
                @Override
                public LanguageBatchResult call(ServiceResponse<LanguageBatchResult> response) {
                    return response.body();
                }
            });
        }
    }


    /**
     * The API returns a list of strings denoting the key talking points in the input text.
     * See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by key phrase extraction.
     *
     * @param keyPhrasesOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the KeyPhraseBatchResult object if successful.
     */
    public KeyPhraseBatchResult keyPhrases(KeyPhrasesOptionalParameter keyPhrasesOptionalParameter) {
        return keyPhrasesWithServiceResponseAsync(keyPhrasesOptionalParameter).toBlocking().single().body();
    }

    /**
     * The API returns a list of strings denoting the key talking points in the input text.
     * See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by key phrase extraction.
     *
     * @param keyPhrasesOptionalParameter the object representing the optional parameters to be set before calling this API
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<KeyPhraseBatchResult> keyPhrasesAsync(KeyPhrasesOptionalParameter keyPhrasesOptionalParameter, final ServiceCallback<KeyPhraseBatchResult> serviceCallback) {
        return ServiceFuture.fromResponse(keyPhrasesWithServiceResponseAsync(keyPhrasesOptionalParameter), serviceCallback);
    }

    /**
     * The API returns a list of strings denoting the key talking points in the input text.
     * See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by key phrase extraction.
     *
     * @param keyPhrasesOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the KeyPhraseBatchResult object
     */
    public Observable<KeyPhraseBatchResult> keyPhrasesAsync(KeyPhrasesOptionalParameter keyPhrasesOptionalParameter) {
        return keyPhrasesWithServiceResponseAsync(keyPhrasesOptionalParameter).map(new Func1<ServiceResponse<KeyPhraseBatchResult>, KeyPhraseBatchResult>() {
            @Override
            public KeyPhraseBatchResult call(ServiceResponse<KeyPhraseBatchResult> response) {
                return response.body();
            }
        });
    }

    /**
     * The API returns a list of strings denoting the key talking points in the input text.
     * See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by key phrase extraction.
     *
     * @param keyPhrasesOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the KeyPhraseBatchResult object
     */
    public Observable<ServiceResponse<KeyPhraseBatchResult>> keyPhrasesWithServiceResponseAsync(KeyPhrasesOptionalParameter keyPhrasesOptionalParameter) {
        if (this.client.azureRegion() == null) {
            throw new IllegalArgumentException("Parameter this.client.azureRegion() is required and cannot be null.");
        }
        final List<MultiLanguageInput> documents = keyPhrasesOptionalParameter != null ? keyPhrasesOptionalParameter.documents() : null;

        return keyPhrasesWithServiceResponseAsync(documents);
    }

    /**
     * The API returns a list of strings denoting the key talking points in the input text.
     * See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by key phrase extraction.
     *
     * @param documents the List&lt;MultiLanguageInput&gt; value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the KeyPhraseBatchResult object
     */
    public Observable<ServiceResponse<KeyPhraseBatchResult>> keyPhrasesWithServiceResponseAsync(List<MultiLanguageInput> documents) {
        if (this.client.azureRegion() == null) {
            throw new IllegalArgumentException("Parameter this.client.azureRegion() is required and cannot be null.");
        }
        Validator.validate(documents);
        MultiLanguageBatchInput input = new MultiLanguageBatchInput();
        input.withDocuments(documents);
        String parameterizedHost = Joiner.on(", ").join("{AzureRegion}", this.client.azureRegion());
        return service.keyPhrases(this.client.acceptLanguage(), input, parameterizedHost, this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<KeyPhraseBatchResult>>>() {
                @Override
                public Observable<ServiceResponse<KeyPhraseBatchResult>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<KeyPhraseBatchResult> clientResponse = keyPhrasesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<KeyPhraseBatchResult> keyPhrasesDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<KeyPhraseBatchResult, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<KeyPhraseBatchResult>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    @Override
    public TextAnalyticsKeyPhrasesParameters keyPhrases() {
        return new TextAnalyticsKeyPhrasesParameters(this);
    }

    /**
     * Internal class implementing TextAnalyticsKeyPhrasesDefinition.
     */
    class TextAnalyticsKeyPhrasesParameters implements TextAnalyticsKeyPhrasesDefinition {
        private TextAnalyticsImpl parent;
        private List<MultiLanguageInput> documents;

        /**
         * Constructor.
         * @param parent the parent object.
         */
        TextAnalyticsKeyPhrasesParameters(TextAnalyticsImpl parent) {
            this.parent = parent;
        }

        @Override
        public TextAnalyticsKeyPhrasesParameters withDocuments(List<MultiLanguageInput> documents) {
            this.documents = documents;
            return this;
        }

        @Override
        public KeyPhraseBatchResult execute() {
        return keyPhrasesWithServiceResponseAsync(documents).toBlocking().single().body();
    }

        @Override
        public Observable<KeyPhraseBatchResult> executeAsync() {
            return keyPhrasesWithServiceResponseAsync(documents).map(new Func1<ServiceResponse<KeyPhraseBatchResult>, KeyPhraseBatchResult>() {
                @Override
                public KeyPhraseBatchResult call(ServiceResponse<KeyPhraseBatchResult> response) {
                    return response.body();
                }
            });
        }
    }

}
