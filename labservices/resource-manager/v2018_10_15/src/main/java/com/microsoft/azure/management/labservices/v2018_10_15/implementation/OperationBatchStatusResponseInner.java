/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.labservices.v2018_10_15.implementation;

import java.util.List;
import com.microsoft.azure.management.labservices.v2018_10_15.OperationBatchStatusResponseItem;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Status Details of the long running operation for an environment.
 */
public class OperationBatchStatusResponseInner {
    /**
     * Gets a collection of items that contain the operation url and status.
     */
    @JsonProperty(value = "items", access = JsonProperty.Access.WRITE_ONLY)
    private List<OperationBatchStatusResponseItem> items;

    /**
     * Get gets a collection of items that contain the operation url and status.
     *
     * @return the items value
     */
    public List<OperationBatchStatusResponseItem> items() {
        return this.items;
    }

}
