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
 * A certificate operation is returned in case of asynchronous requests.
 */
public class CertificateOperation extends com.microsoft.azure.keyvault.models.custom.CertificateOperation {
    /**
     * The certificate id.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /**
     * Parameters for the issuer of the X509 component of a certificate.
     */
    @JsonProperty(value = "issuer")
    private IssuerParameters issuerParameters;

    /**
     * The certificate signing request (CSR) that is being used in the
     * certificate operation.
     */
    @JsonProperty(value = "csr")
    private byte[] csr;

    /**
     * Indicates if cancellation was requested on the certificate operation.
     */
    @JsonProperty(value = "cancellation_requested")
    private Boolean cancellationRequested;

    /**
     * Status of the certificate operation.
     */
    @JsonProperty(value = "status")
    private String status;

    /**
     * The status details of the certificate operation.
     */
    @JsonProperty(value = "status_details")
    private String statusDetails;

    /**
     * Error encountered, if any, during the certificate operation.
     */
    @JsonProperty(value = "error")
    private Error error;

    /**
     * Location which contains the result of the certificate operation.
     */
    @JsonProperty(value = "target")
    private String target;

    /**
     * Identifier for the certificate operation.
     */
    @JsonProperty(value = "request_id")
    private String requestId;

    /**
     * Get the certificate id.
     *
     * @return the id value
     */
    public String id() {
        return this.id;
    }

    /**
     * Get parameters for the issuer of the X509 component of a certificate.
     *
     * @return the issuerParameters value
     */
    public IssuerParameters issuerParameters() {
        return this.issuerParameters;
    }

    /**
     * Set parameters for the issuer of the X509 component of a certificate.
     *
     * @param issuerParameters the issuerParameters value to set
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation withIssuerParameters(IssuerParameters issuerParameters) {
        this.issuerParameters = issuerParameters;
        return this;
    }

    /**
     * Get the certificate signing request (CSR) that is being used in the certificate operation.
     *
     * @return the csr value
     */
    public byte[] csr() {
        return this.csr;
    }

    /**
     * Set the certificate signing request (CSR) that is being used in the certificate operation.
     *
     * @param csr the csr value to set
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation withCsr(byte[] csr) {
        this.csr = csr;
        return this;
    }

    /**
     * Get indicates if cancellation was requested on the certificate operation.
     *
     * @return the cancellationRequested value
     */
    public Boolean cancellationRequested() {
        return this.cancellationRequested;
    }

    /**
     * Set indicates if cancellation was requested on the certificate operation.
     *
     * @param cancellationRequested the cancellationRequested value to set
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation withCancellationRequested(Boolean cancellationRequested) {
        this.cancellationRequested = cancellationRequested;
        return this;
    }

    /**
     * Get status of the certificate operation.
     *
     * @return the status value
     */
    public String status() {
        return this.status;
    }

    /**
     * Set status of the certificate operation.
     *
     * @param status the status value to set
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Get the status details of the certificate operation.
     *
     * @return the statusDetails value
     */
    public String statusDetails() {
        return this.statusDetails;
    }

    /**
     * Set the status details of the certificate operation.
     *
     * @param statusDetails the statusDetails value to set
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation withStatusDetails(String statusDetails) {
        this.statusDetails = statusDetails;
        return this;
    }

    /**
     * Get error encountered, if any, during the certificate operation.
     *
     * @return the error value
     */
    public Error error() {
        return this.error;
    }

    /**
     * Set error encountered, if any, during the certificate operation.
     *
     * @param error the error value to set
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation withError(Error error) {
        this.error = error;
        return this;
    }

    /**
     * Get location which contains the result of the certificate operation.
     *
     * @return the target value
     */
    public String target() {
        return this.target;
    }

    /**
     * Set location which contains the result of the certificate operation.
     *
     * @param target the target value to set
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation withTarget(String target) {
        this.target = target;
        return this;
    }

    /**
     * Get identifier for the certificate operation.
     *
     * @return the requestId value
     */
    public String requestId() {
        return this.requestId;
    }

    /**
     * Set identifier for the certificate operation.
     *
     * @param requestId the requestId value to set
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

}
