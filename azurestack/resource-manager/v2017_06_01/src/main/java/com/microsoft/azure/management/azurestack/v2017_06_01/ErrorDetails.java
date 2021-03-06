/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.azurestack.v2017_06_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The details of the error.
 */
public class ErrorDetails {
    /**
     * Error code.
     */
    @JsonProperty(value = "code", access = JsonProperty.Access.WRITE_ONLY)
    private String code;

    /**
     * Error message indicating why the operation failed.
     */
    @JsonProperty(value = "message", access = JsonProperty.Access.WRITE_ONLY)
    private String message;

    /**
     * The target of the particular error.
     */
    @JsonProperty(value = "target", access = JsonProperty.Access.WRITE_ONLY)
    private String target;

    /**
     * Get error code.
     *
     * @return the code value
     */
    public String code() {
        return this.code;
    }

    /**
     * Get error message indicating why the operation failed.
     *
     * @return the message value
     */
    public String message() {
        return this.message;
    }

    /**
     * Get the target of the particular error.
     *
     * @return the target value
     */
    public String target() {
        return this.target;
    }

}
