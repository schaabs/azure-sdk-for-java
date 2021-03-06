// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.keyvault.keys;

import com.azure.core.credentials.AccessToken;
import com.azure.core.credentials.TokenCredential;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.test.TestBase;
import com.azure.keyvault.keys.models.DeletedKey;
import com.azure.keyvault.keys.models.Key;
import com.azure.keyvault.keys.models.KeyBase;
import com.azure.keyvault.keys.models.KeyCreateOptions;
import com.azure.keyvault.keys.models.webkey.KeyType;
import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public abstract class KeyClientTestBase extends TestBase {

    private static final String KEY_NAME = "javaKeyTemp";
    private static final KeyType RSA_KEY_TYPE = KeyType.RSA;
    private static final KeyType EC_KEY_TYPE = KeyType.EC;


    @Rule
    public TestName testName = new TestName();

    @Override
    protected String testName() {
        return testName.getMethodName();
    }

    void beforeTestSetup() {
    }

    <T> T clientSetup(Function<TokenCredential, T> clientBuilder) {
        final String endpoint = interceptorManager.isPlaybackMode()
                ? "http://localhost:8080"
                : System.getenv("AZURE_KEYVAULT_ENDPOINT");

        final String tenantId = interceptorManager.isPlaybackMode()
                ? "some-tenant-id"
                : System.getenv("MICROSOFT_AD_TENANT_ID");

        final String clientId = interceptorManager.isPlaybackMode()
                ? "some-client-id"
                : System.getenv("ARM_CLIENT_ID");

        final String clientKey = interceptorManager.isPlaybackMode()
                ? "http://localhost:8080"
                : System.getenv("ARM_CLIENT_KEY");

        Objects.requireNonNull(endpoint, "AZURE_KEYVAULT_ENDPOINT expected to be set.");
        Objects.requireNonNull(clientId, "ARM_CLIENT_ID expected to be set.");
        Objects.requireNonNull(clientKey, "ARM_CLIENT_KEY expected to be set.");
        Objects.requireNonNull(tenantId, "MICROSOFT_AD_TENANT_ID expected to be set.");

        TokenCredential credential = resource -> {
            if (interceptorManager.isPlaybackMode()) {
                return Mono.just(new AccessToken("Some fake token", OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofMinutes(30))));
            }

            try {
                return Mono.just(getAccessToken(tenantId, clientId, clientKey));
            } catch (Exception e) {
                return Mono.error(e);
            }
        };

        T client;
        String authority = "https://login.microsoftonline.com/{tenantId}";
        String auth = authority.replace("{tenantId}", tenantId);
        client = clientBuilder.apply(credential);

        return Objects.requireNonNull(client);
    }

    private AccessToken getAccessToken(String tenantId, String clientId, String clientKey) throws MalformedURLException, ExecutionException, InterruptedException {
        String authority = "https://login.microsoftonline.com/{tenantId}";
        String auth = authority.replace("{tenantId}", tenantId);

        ExecutorService service = Executors.newFixedThreadPool(1);
        AuthenticationContext context = new AuthenticationContext(auth, true, service);
        // Acquire Token
        Future<AuthenticationResult> result = context.acquireToken(
                "https://vault.azure.net",
                new ClientCredential(clientId, clientKey),
                null
        );

        final AuthenticationResult authenticationResult = result.get();
        final String token = authenticationResult.getAccessToken();
        final OffsetDateTime expiresOn = authenticationResult.getExpiresOnDate().toInstant().atOffset(ZoneOffset.UTC);

        return new AccessToken(token, expiresOn);
    }

    @Test
    public abstract void setKey();

    void setKeyRunner(Consumer<KeyCreateOptions> testRunner) {
        final Map<String, String> tags = new HashMap<>();

        tags.put("foo", "baz");

        final KeyCreateOptions keyOptions = new KeyCreateOptions(KEY_NAME, RSA_KEY_TYPE)
            .expires(OffsetDateTime.of(2050, 1, 30, 0, 0, 0, 0, ZoneOffset.UTC))
            .notBefore(OffsetDateTime.of(2000, 1, 30, 12, 59, 59, 0, ZoneOffset.UTC))
            .tags(tags);

        testRunner.accept(keyOptions);
    }

    @Test
    public abstract void setKeyEmptyName();

    @Test
    public abstract void setKeyNullType();

    void setKeyEmptyValueRunner(Consumer<KeyCreateOptions> testRunner) {
        KeyCreateOptions key = new KeyCreateOptions(KEY_NAME, null);
        testRunner.accept(key);
    }

    @Test public abstract void setKeyNull();


    @Test
    public abstract void updateKey();

    void updateKeyRunner(BiConsumer<KeyCreateOptions, KeyCreateOptions> testRunner) {

        final Map<String, String> tags = new HashMap<>();
        tags.put("first tag", "first value");
        tags.put("second tag", "second value");
        final KeyCreateOptions originalKey = new KeyCreateOptions("testKey1", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC))
                .tags(tags);

        final KeyCreateOptions updatedKey = new KeyCreateOptions("testKey1", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2060, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC))
                .tags(tags);

        testRunner.accept(originalKey, updatedKey);
    }


    @Test
    public abstract void updateDisabledKey();

    void updateDisabledKeyRunner(BiConsumer<KeyCreateOptions, KeyCreateOptions> testRunner) {

        final Map<String, String> tags = new HashMap<>();

        final KeyCreateOptions originalKey = new KeyCreateOptions("testKey2", EC_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC))
                .enabled(false);

        final KeyCreateOptions updatedKey = new KeyCreateOptions("testKey2", EC_KEY_TYPE)
                .expires(OffsetDateTime.of(2060, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));

        testRunner.accept(originalKey, updatedKey);
    }

    @Test
    public abstract void getKey();

    void getKeyRunner(Consumer<KeyCreateOptions> testRunner) {
        final KeyCreateOptions originalKey = new KeyCreateOptions("testKey4", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));

        testRunner.accept(originalKey);
    }

    @Test
    public abstract void getKeySpecificVersion();

    void getKeySpecificVersionRunner(BiConsumer<KeyCreateOptions, KeyCreateOptions> testRunner) {
        final KeyCreateOptions key = new KeyCreateOptions("testKey3", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));

        final KeyCreateOptions keyWithNewVal = new KeyCreateOptions("testKey3", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));

        testRunner.accept(key, keyWithNewVal);
    }

    @Test
    public abstract void getKeyNotFound();

    @Test
    public abstract void deleteKey();

    void deleteKeyRunner(Consumer<KeyCreateOptions> testRunner) {
        final KeyCreateOptions keyToDelete = new KeyCreateOptions("testKey5", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));

        testRunner.accept(keyToDelete);
    }

    @Test
    public abstract void deleteKeyNotFound();

    @Test
    public abstract void getDeletedKey();

    void getDeletedKeyRunner(Consumer<KeyCreateOptions> testRunner) {
        final KeyCreateOptions keyToDeleteAndGet = new KeyCreateOptions("testKey6", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));
        testRunner.accept(keyToDeleteAndGet);
    }

    @Test
    public abstract void getDeletedKeyNotFound();

    @Test
    public abstract void recoverDeletedKey();

    void recoverDeletedKeyRunner(Consumer<KeyCreateOptions> testRunner) {
        final KeyCreateOptions keyToDeleteAndRecover = new KeyCreateOptions("testKey7", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));
        testRunner.accept(keyToDeleteAndRecover);
    }

    @Test
    public abstract void recoverDeletedKeyNotFound();

    @Test
    public abstract void backupKey();

    void backupKeyRunner(Consumer<KeyCreateOptions> testRunner) {
        final KeyCreateOptions keyToBackup = new KeyCreateOptions("testKey8", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));
        testRunner.accept(keyToBackup);
    }

    @Test
    public abstract void backupKeyNotFound();

    @Test
    public abstract void restoreKey();

    void restoreKeyRunner(Consumer<KeyCreateOptions> testRunner) {
        final KeyCreateOptions keyToBackupAndRestore = new KeyCreateOptions("testKey9", RSA_KEY_TYPE)
                .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));
        testRunner.accept(keyToBackupAndRestore);
    }

    @Test
    public abstract void restoreKeyFromMalformedBackup();

//    @Test
//    public abstract void listKeys();
//
//    void listKeysRunner(Consumer<HashMap<String, KeyCreateOptions>> testRunner) {
//        HashMap<String, KeyCreateOptions> keys = new HashMap<>();
//        String keyName;
//        String keyVal;
//        for(int i = 0; i < 10; i++) {
//            keyName = "listKey" + i;
//            keys.put(keyName, new KeyCreateOptions(keyName, RSA_KEY_TYPE)
//                    .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC)));
//
//        }
//        testRunner.accept(keys);
//    }

    @Test
    public abstract void listKeys();

    void listKeysRunner(Function<List<KeyCreateOptions>, List<KeyBase>> testRunner) {
        HashMap<String, KeyCreateOptions> keys = new HashMap<>();
        List<KeyCreateOptions> keysList = new ArrayList<>();
        String keyName;
        for (int i = 0; i < 30; i++) {
            keyName = "listKey" + i;
            KeyCreateOptions secret =  new KeyCreateOptions(keyName, RSA_KEY_TYPE)
                    .expires(OffsetDateTime.of(2050, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC));
            keys.put(keyName, secret);
            keysList.add(secret);
        }
        for (KeyBase actualSecret : testRunner.apply(keysList)) {
            if (keys.containsKey(actualSecret.name())) {
                KeyCreateOptions expectedSecret = keys.get(actualSecret.name());
                assertEquals(expectedSecret.expires(), actualSecret.expires());
                assertEquals(expectedSecret.notBefore(), actualSecret.notBefore());
                keys.remove(actualSecret.name());
            }
        }
        assertEquals(0, keys.size());
    }

    @Test
    public abstract void listDeletedKeys();

    void listDeletedKeysRunner(Function<HashMap<String, KeyCreateOptions>, List<DeletedKey>> testRunner) {
        HashMap<String, KeyCreateOptions> secrets = new HashMap<>();
        String keyName;
        for (int i = 0; i < 3; i++) {
            keyName = "listDeletedKeysTest" + i;
            secrets.put(keyName, new KeyCreateOptions(keyName, RSA_KEY_TYPE)
                    .expires(OffsetDateTime.of(2090, 5, 25, 0, 0, 0, 0, ZoneOffset.UTC)));

        }
        for (DeletedKey actualSecret : testRunner.apply(secrets)) {
            if (secrets.containsKey(actualSecret.name())) {
                assertNotNull(actualSecret.deletedDate());
                assertNotNull(actualSecret.recoveryId());
                secrets.remove(actualSecret.name());
            }
        }
        assertEquals(0, secrets.size());
    }

    @Test
    public abstract void listKeyVersions();

    void listKeyVersionsRunner(Function<List<KeyCreateOptions>, List<KeyBase>> testRunner) {
        List<KeyCreateOptions> keys = new ArrayList<>();
        String keyName;
        for (int i = 1; i < 5; i++) {
            keyName = "listKeyVersion";
            keys.add(new KeyCreateOptions(keyName, RSA_KEY_TYPE)
                    .expires(OffsetDateTime.of(2090, 5, i, 0, 0, 0, 0, ZoneOffset.UTC)));
        }
        assertEquals(4, testRunner.apply(keys).size());
    }

    /**
     * Helper method to verify that the Response matches what was expected. This method assumes a response status of 200.
     *
     * @param expected Key expected to be returned by the service
     * @param response Response returned by the service, the body should contain a Key
     */
    static void assertKeyEquals(KeyCreateOptions expected, Response<Key> response) {
        assertKeyEquals(expected, response, 200);
    }

    /**
     * Helper method to verify that the RestResponse matches what was expected.
     *
     * @param expected ConfigurationSetting expected to be returned by the service
     * @param response RestResponse returned from the service, the body should contain a ConfigurationSetting
     * @param expectedStatusCode Expected HTTP status code returned by the service
     */
    static void assertKeyEquals(KeyCreateOptions expected, Response<Key> response, final int expectedStatusCode) {
        assertNotNull(response);
        assertEquals(expectedStatusCode, response.statusCode());

        assertKeyEquals(expected, response.value());
    }

    /**
     * Helper method to verify that the returned ConfigurationSetting matches what was expected.
     *
     * @param expected ConfigurationSetting expected to be returned by the service
     * @param actual ConfigurationSetting contained in the RestResponse body
     */
    static void assertKeyEquals(KeyCreateOptions expected, Key actual) {
        assertEquals(expected.name(), actual.name());
        assertEquals(expected.keyType(), actual.keyMaterial().kty());
        assertEquals(expected.expires(), actual.expires());
        assertEquals(expected.notBefore(), actual.notBefore());
    }

    public String getEndpoint() {
        final String endpoint = interceptorManager.isPlaybackMode()
                ? "http://localhost:8080"
                : System.getenv("AZURE_KEYVAULT_ENDPOINT");
        Objects.requireNonNull(endpoint);
        return endpoint;
    }

    static void assertRestException(Runnable exceptionThrower, int expectedStatusCode) {
        assertRestException(exceptionThrower, HttpResponseException.class, expectedStatusCode);
    }

    static void assertRestException(Runnable exceptionThrower, Class<? extends HttpResponseException> expectedExceptionType, int expectedStatusCode) {
        try {
            exceptionThrower.run();
            fail();
        } catch (Throwable ex) {
            assertRestException(ex, expectedExceptionType, expectedStatusCode);
        }
    }

    /**
     * Helper method to verify the error was a HttpRequestException and it has a specific HTTP response code.
     *
     * @param exception Expected error thrown during the test
     * @param expectedStatusCode Expected HTTP status code contained in the error response
     */
    static void assertRestException(Throwable exception, int expectedStatusCode) {
        assertRestException(exception, HttpResponseException.class, expectedStatusCode);
    }

    static void assertRestException(Throwable exception, Class<? extends HttpResponseException> expectedExceptionType, int expectedStatusCode) {
        assertEquals(expectedExceptionType, exception.getClass());
        assertEquals(expectedStatusCode, ((HttpResponseException) exception).response().statusCode());
    }

    /**
     * Helper method to verify that a command throws an IllegalArgumentException.
     *
     * @param exceptionThrower Command that should throw the exception
     */
    static <T> void assertRunnableThrowsException(Runnable exceptionThrower, Class<T> exception) {
        try {
            exceptionThrower.run();
            fail();
        } catch (Exception ex) {
            assertEquals(exception, ex.getClass());
        }
    }

    public void sleepInRecordMode(long millis) {
        if (interceptorManager.isPlaybackMode()) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
