/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.iothub.v2018_12_01_preview;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.management.iothub.v2018_12_01_preview.implementation.IotHubSkuDescriptionInner;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.iothub.v2018_12_01_preview.implementation.IoTHubManager;

/**
 * Type representing IotHubSkuDescription.
 */
public interface IotHubSkuDescription extends HasInner<IotHubSkuDescriptionInner>, HasManager<IoTHubManager> {
    /**
     * @return the capacity value.
     */
    IotHubCapacity capacity();

    /**
     * @return the resourceType value.
     */
    String resourceType();

    /**
     * @return the sku value.
     */
    IotHubSkuInfo sku();

}
