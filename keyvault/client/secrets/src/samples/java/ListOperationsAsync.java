// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

import com.azure.keyvault.SecretAsyncClient;
import com.azure.keyvault.models.Secret;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;

/**
 * Sample demonstrates how to asynchronously list secrets and versions of a given secret in the key vault.
 */
public class ListOperationsAsync {
    /**
     * Authenticates with the key vault and shows how to asynchronously list secrets and list versions of a specific secret in the key vault.
     *
     * @param args Unused. Arguments to the program.
     * @throws IllegalArgumentException when invalid key vault endpoint is passed.
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InterruptedException {

        // Instantiate an async secret client that will be used to call the service. Notice that the client is using default Azure
        // credentials. To make default credentials work, ensure that environment variables 'AZURE_CLIENT_ID',
        // 'AZURE_CLIENT_KEY' and 'AZURE_TENANT_ID' are set with the service principal credentials.
        SecretAsyncClient secretAsyncClient = SecretAsyncClient.builder()
                .endpoint("https://{YOUR_VAULT_NAME}.vault.azure.net")
                //.credential(AzureCredential.DEFAULT)
                .build();

        // Let's create secrets holding storage and bank accounts credentials valid for 1 year. if the secret
        // already exists in the key vault, then a new version of the secret is created.
        secretAsyncClient.setSecret(new Secret("BankAccountPassword", "f4G34fMh8v")
                .expires(OffsetDateTime.now().plusYears(1)))
                .subscribe(secretResponse ->
                        System.out.printf("Secret is created with name %s and value %s \n", secretResponse.value().name(), secretResponse.value().value()));

        Thread.sleep(2000);

        secretAsyncClient.setSecret(new Secret("StorageAccountPassword", "f4G34fMh8v-fdsgjsk2323=-asdsdfsdf")
                .expires(OffsetDateTime.now().plusYears(1)))
                .subscribe(secretResponse ->
                        System.out.printf("Secret is created with name %s and value %s \n", secretResponse.value().name(), secretResponse.value().value()));

        Thread.sleep(2000);

        // You need to check if any of the secrets are sharing same values. Let's list the secrets and print their values.
        // List operations don't return the secrets with value information. So, for each returned secret we call getSecret to get the secret with its value information.
        secretAsyncClient.listSecrets()
          .subscribe(secretBase ->
            secretAsyncClient.getSecret(secretBase).subscribe(secretResponse ->
                  System.out.printf("Received secret with name %s and value %s \n", secretResponse.value().name(), secretResponse.value().value())));

        Thread.sleep(15000);

        // The bank account password got updated, so you want to update the secret in key vault to ensure it reflects the new password.
        // Calling setSecret on an existing secret creates a new version of the secret in the key vault with the new value.
        secretAsyncClient.setSecret(new Secret("BankAccountPassword", "sskdjfsdasdjsd")
          .expires(OffsetDateTime.now().plusYears(1))).subscribe(secretResponse ->
                System.out.printf("Secret is created with name %s and value %s \n", secretResponse.value().name(), secretResponse.value().value()));

        Thread.sleep(2000);

        // You need to check all the different values your bank account password secret had previously. Lets print all the versions of this secret.
        secretAsyncClient.listSecretVersions("BankAccountPassword").subscribe(secretBase ->
            secretAsyncClient.getSecret(secretBase).subscribe(secretResponse ->
                System.out.printf("Received secret's version with name %s and value %s \n", secretResponse.value().name(), secretResponse.value().value())));

        Thread.sleep(15000);
    }
}
