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
 * The issuer for Key Vault certificate.
 */
public class IssuerBundle extends com.microsoft.azure.keyvault.models.custom.IssuerBundle {
    /**
     * Identifier for the issuer object.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /**
     * The issuer provider.
     */
    @JsonProperty(value = "provider")
    private String provider;

    /**
     * The credentials to be used for the issuer.
     */
    @JsonProperty(value = "credentials")
    private IssuerCredentials credentials;

    /**
     * Details of the organization as provided to the issuer.
     */
    @JsonProperty(value = "org_details")
    private OrganizationDetails organizationDetails;

    /**
     * Attributes of the issuer object.
     */
    @JsonProperty(value = "attributes")
    private IssuerAttributes attributes;

    /**
     * Get identifier for the issuer object.
     *
     * @return the id value
     */
    public String id() {
        return this.id;
    }

    /**
     * Get the issuer provider.
     *
     * @return the provider value
     */
    public String provider() {
        return this.provider;
    }

    /**
     * Set the issuer provider.
     *
     * @param provider the provider value to set
     * @return the IssuerBundle object itself.
     */
    public IssuerBundle withProvider(String provider) {
        this.provider = provider;
        return this;
    }

    /**
     * Get the credentials to be used for the issuer.
     *
     * @return the credentials value
     */
    public IssuerCredentials credentials() {
        return this.credentials;
    }

    /**
     * Set the credentials to be used for the issuer.
     *
     * @param credentials the credentials value to set
     * @return the IssuerBundle object itself.
     */
    public IssuerBundle withCredentials(IssuerCredentials credentials) {
        this.credentials = credentials;
        return this;
    }

    /**
     * Get details of the organization as provided to the issuer.
     *
     * @return the organizationDetails value
     */
    public OrganizationDetails organizationDetails() {
        return this.organizationDetails;
    }

    /**
     * Set details of the organization as provided to the issuer.
     *
     * @param organizationDetails the organizationDetails value to set
     * @return the IssuerBundle object itself.
     */
    public IssuerBundle withOrganizationDetails(OrganizationDetails organizationDetails) {
        this.organizationDetails = organizationDetails;
        return this;
    }

    /**
     * Get attributes of the issuer object.
     *
     * @return the attributes value
     */
    public IssuerAttributes attributes() {
        return this.attributes;
    }

    /**
     * Set attributes of the issuer object.
     *
     * @param attributes the attributes value to set
     * @return the IssuerBundle object itself.
     */
    public IssuerBundle withAttributes(IssuerAttributes attributes) {
        this.attributes = attributes;
        return this;
    }

}
