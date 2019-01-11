/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.keyvault.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Properties of the key backing a certificate.
 */
public class SecretProperties {
    /**
     * The media type (MIME type).
     */
    @JsonProperty(value = "contentType")
    private String contentType;

    /**
     * Get the media type (MIME type).
     *
     * @return the contentType value
     */
    public String contentType() {
        return this.contentType;
    }

    /**
     * Set the media type (MIME type).
     *
     * @param contentType the contentType value to set
     * @return the SecretProperties object itself.
     */
    public SecretProperties withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

}
