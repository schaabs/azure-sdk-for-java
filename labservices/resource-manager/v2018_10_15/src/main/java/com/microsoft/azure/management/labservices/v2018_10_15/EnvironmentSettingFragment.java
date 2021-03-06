/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.labservices.v2018_10_15;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.rest.SkipParentValidation;
import com.microsoft.azure.Resource;

/**
 * Represents settings of an environment, from which environment instances
 * would be created.
 */
@JsonFlatten
@SkipParentValidation
public class EnvironmentSettingFragment extends Resource {
    /**
     * Describes the user's progress in configuring their environment setting.
     * Possible values include: 'NotApplicable', 'Completed'.
     */
    @JsonProperty(value = "properties.configurationState")
    private ConfigurationState configurationState;

    /**
     * Describes the environment and its resource settings.
     */
    @JsonProperty(value = "properties.description")
    private String description;

    /**
     * Brief title describing the environment and its resource settings.
     */
    @JsonProperty(value = "properties.title")
    private String title;

    /**
     * The resource specific settings.
     */
    @JsonProperty(value = "properties.resourceSettings")
    private ResourceSettingsFragment resourceSettings;

    /**
     * The provisioning status of the resource.
     */
    @JsonProperty(value = "properties.provisioningState")
    private String provisioningState;

    /**
     * The unique immutable identifier of a resource (Guid).
     */
    @JsonProperty(value = "properties.uniqueIdentifier")
    private String uniqueIdentifier;

    /**
     * Get describes the user's progress in configuring their environment setting. Possible values include: 'NotApplicable', 'Completed'.
     *
     * @return the configurationState value
     */
    public ConfigurationState configurationState() {
        return this.configurationState;
    }

    /**
     * Set describes the user's progress in configuring their environment setting. Possible values include: 'NotApplicable', 'Completed'.
     *
     * @param configurationState the configurationState value to set
     * @return the EnvironmentSettingFragment object itself.
     */
    public EnvironmentSettingFragment withConfigurationState(ConfigurationState configurationState) {
        this.configurationState = configurationState;
        return this;
    }

    /**
     * Get describes the environment and its resource settings.
     *
     * @return the description value
     */
    public String description() {
        return this.description;
    }

    /**
     * Set describes the environment and its resource settings.
     *
     * @param description the description value to set
     * @return the EnvironmentSettingFragment object itself.
     */
    public EnvironmentSettingFragment withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get brief title describing the environment and its resource settings.
     *
     * @return the title value
     */
    public String title() {
        return this.title;
    }

    /**
     * Set brief title describing the environment and its resource settings.
     *
     * @param title the title value to set
     * @return the EnvironmentSettingFragment object itself.
     */
    public EnvironmentSettingFragment withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the resource specific settings.
     *
     * @return the resourceSettings value
     */
    public ResourceSettingsFragment resourceSettings() {
        return this.resourceSettings;
    }

    /**
     * Set the resource specific settings.
     *
     * @param resourceSettings the resourceSettings value to set
     * @return the EnvironmentSettingFragment object itself.
     */
    public EnvironmentSettingFragment withResourceSettings(ResourceSettingsFragment resourceSettings) {
        this.resourceSettings = resourceSettings;
        return this;
    }

    /**
     * Get the provisioning status of the resource.
     *
     * @return the provisioningState value
     */
    public String provisioningState() {
        return this.provisioningState;
    }

    /**
     * Set the provisioning status of the resource.
     *
     * @param provisioningState the provisioningState value to set
     * @return the EnvironmentSettingFragment object itself.
     */
    public EnvironmentSettingFragment withProvisioningState(String provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    /**
     * Get the unique immutable identifier of a resource (Guid).
     *
     * @return the uniqueIdentifier value
     */
    public String uniqueIdentifier() {
        return this.uniqueIdentifier;
    }

    /**
     * Set the unique immutable identifier of a resource (Guid).
     *
     * @param uniqueIdentifier the uniqueIdentifier value to set
     * @return the EnvironmentSettingFragment object itself.
     */
    public EnvironmentSettingFragment withUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
        return this;
    }

}
