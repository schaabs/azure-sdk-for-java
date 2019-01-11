/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.keyvault.models;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The SAS definition item containing storage SAS definition metadata.
 */
public class SasDefinitionItem {
    /**
     * The storage SAS identifier.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /**
     * The storage account SAS definition secret id.
     */
    @JsonProperty(value = "sid", access = JsonProperty.Access.WRITE_ONLY)
    private String secretId;

    /**
     * The SAS definition management attributes.
     */
    @JsonProperty(value = "attributes", access = JsonProperty.Access.WRITE_ONLY)
    private SasDefinitionAttributes attributes;

    /**
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags", access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, String> tags;

    /**
     * Get the storage SAS identifier.
     *
     * @return the id value
     */
    public String id() {
        return this.id;
    }

    /**
     * Get the storage account SAS definition secret id.
     *
     * @return the secretId value
     */
    public String secretId() {
        return this.secretId;
    }

    /**
     * Get the SAS definition management attributes.
     *
     * @return the attributes value
     */
    public SasDefinitionAttributes attributes() {
        return this.attributes;
    }

    /**
     * Get application specific metadata in the form of key-value pairs.
     *
     * @return the tags value
     */
    public Map<String, String> tags() {
        return this.tags;
    }

}
