/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.recoveryservices.backup.v2016_06_01;

import rx.Observable;
import com.microsoft.azure.management.recoveryservices.backup.v2016_06_01.implementation.JobDetailsInner;
import com.microsoft.azure.arm.model.HasInner;

/**
 * Type representing JobDetails.
 */
public interface JobDetails extends HasInner<JobDetailsInner> {
    /**
     * Gets extended information associated with the job.
     *
     * @param vaultName The name of the Recovery Services vault.
     * @param resourceGroupName The name of the resource group associated with the Recovery Services vault.
     * @param jobName Name of the job associated with this GET operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<VaultJobResourceModel> getAsync(String vaultName, String resourceGroupName, String jobName);

}
