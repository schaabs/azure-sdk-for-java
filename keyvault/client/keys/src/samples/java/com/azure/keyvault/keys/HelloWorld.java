// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.keyvault.keys;

import com.azure.keyvault.keys.models.Key;
import com.azure.keyvault.keys.models.RsaKeyCreateOptions;

import java.time.OffsetDateTime;

/**
 * Sample demonstrates how to set, get, update and delete a key.
 */
public class HelloWorld {

    /**
     * Authenticates with the key vault and shows how to set, get, update and delete a key in the key vault.
     *
     * @param args Unused. Arguments to the program.
     * @throws IllegalArgumentException when invalid key vault endpoint is passed.
     * @throws InterruptedException when the thread is interrupted in sleep mode.
     */
    public static void main(String[] args) throws InterruptedException, IllegalArgumentException {

        // Instantiate a key client that will be used to call the service. Notice that the client is using default Azure
        // credentials. To make default credentials work, ensure that environment variables 'AZURE_CLIENT_ID',
        // 'AZURE_CLIENT_KEY' and 'AZURE_TENANT_ID' are set with the service principal credentials.
        KeyClient keyClient = KeyClient.builder()
                .endpoint("https://{YOUR_VAULT_NAME}.vault.azure.net")
                //.credential(AzureCredential.DEFAULT)
                .build();

        // Let's create a Rsa key valid for 1 year. if the key
        // already exists in the key vault, then a new version of the key is created.
        keyClient.createRsaKey(new RsaKeyCreateOptions("CloudRsaKey")
                .expires(OffsetDateTime.now().plusYears(1))
                .keySize(2048));

        // Let's Get the Cloud Rsa Key from the key vault.
        Key cloudRsaKey = keyClient.getKey("CloudRsaKey").value();
        System.out.printf("Key is returned with name %s and type %s \n", cloudRsaKey.name(), cloudRsaKey.keyMaterial().kty());

        // After one year, the Cloud Rsa Key is still required, we need to update the expiry time of the key.
        // The update method can be used to update the expiry attribute of the key.
        cloudRsaKey.expires(cloudRsaKey.expires().plusYears(1));
        Key updatedKey = keyClient.updateKey(cloudRsaKey).value();
        System.out.printf("Key's updated expiry time %s \n", updatedKey.expires());

        // We need the Cloud Rsa key with bigger key size, so you want to update the key in key vault to ensure it has the required size.
        // Calling createRsaKey on an existing key creates a new version of the key in the key vault with the new specified size.
        keyClient.createRsaKey(new RsaKeyCreateOptions("CloudRsaKey")
                .expires(OffsetDateTime.now().plusYears(1))
                .keySize(4096));

        // The Cloud Rsa Key is no longer needed, need to delete it from the key vault.
        keyClient.deleteKey("CloudRsaKey");

        // To ensure key is deleted on server side.
        Thread.sleep(30000);

        // If the keyvault is soft-delete enabled, then for permanent deletion  deleted keys need to be purged.
        keyClient.purgeDeletedKey("CloudRsaKey");
    }
}
