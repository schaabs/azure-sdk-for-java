/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.keyvault.models;

import com.microsoft.azure.keyvault.webkey.JsonWebKey;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The key import parameters.
 */
public class KeyImportParameters {
    /**
     * Whether to import as a hardware key (HSM) or software key.
     */
    @JsonProperty(value = "Hsm")
    private Boolean hsm;

    /**
     * The Json web key.
     */
    @JsonProperty(value = "key", required = true)
    private JsonWebKey key;

    /**
     * The key management attributes.
     */
    @JsonProperty(value = "attributes")
    private KeyAttributes keyAttributes;

    /**
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * Get whether to import as a hardware key (HSM) or software key.
     *
     * @return the hsm value
     */
    public Boolean hsm() {
        return this.hsm;
    }

    /**
     * Set whether to import as a hardware key (HSM) or software key.
     *
     * @param hsm the hsm value to set
     * @return the KeyImportParameters object itself.
     */
    public KeyImportParameters withHsm(Boolean hsm) {
        this.hsm = hsm;
        return this;
    }

    /**
     * Get the Json web key.
     *
     * @return the key value
     */
    public JsonWebKey key() {
        return this.key;
    }

    /**
     * Set the Json web key.
     *
     * @param key the key value to set
     * @return the KeyImportParameters object itself.
     */
    public KeyImportParameters withKey(JsonWebKey key) {
        this.key = key;
        return this;
    }

    /**
     * Get the key management attributes.
     *
     * @return the keyAttributes value
     */
    public KeyAttributes keyAttributes() {
        return this.keyAttributes;
    }

    /**
     * Set the key management attributes.
     *
     * @param keyAttributes the keyAttributes value to set
     * @return the KeyImportParameters object itself.
     */
    public KeyImportParameters withKeyAttributes(KeyAttributes keyAttributes) {
        this.keyAttributes = keyAttributes;
        return this;
    }

    /**
     * Get application specific metadata in the form of key-value pairs.
     *
     * @return the tags value
     */
    public Map<String, String> tags() {
        return this.tags;
    }

    /**
     * Set application specific metadata in the form of key-value pairs.
     *
     * @param tags the tags value to set
     * @return the KeyImportParameters object itself.
     */
    public KeyImportParameters withTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

}
