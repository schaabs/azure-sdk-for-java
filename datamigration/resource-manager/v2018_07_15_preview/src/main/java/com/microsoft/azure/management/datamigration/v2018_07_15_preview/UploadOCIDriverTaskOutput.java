/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.datamigration.v2018_07_15_preview;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Output for the service task to upload an OCI driver.
 */
public class UploadOCIDriverTaskOutput {
    /**
     * The name of the driver package that was validated and uploaded.
     */
    @JsonProperty(value = "driverPackageName", access = JsonProperty.Access.WRITE_ONLY)
    private String driverPackageName;

    /**
     * Validation errors.
     */
    @JsonProperty(value = "validationErrors", access = JsonProperty.Access.WRITE_ONLY)
    private List<ReportableException> validationErrors;

    /**
     * Get the name of the driver package that was validated and uploaded.
     *
     * @return the driverPackageName value
     */
    public String driverPackageName() {
        return this.driverPackageName;
    }

    /**
     * Get validation errors.
     *
     * @return the validationErrors value
     */
    public List<ReportableException> validationErrors() {
        return this.validationErrors;
    }

}