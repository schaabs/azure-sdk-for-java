// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.core.auth.credentials;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Type representing response from the local MSI token provider.
 */
class MSIToken {

    private static OffsetDateTime epoch = OffsetDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

    @JsonProperty(value = "token_type")
    private String tokenType;

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_on")
    private String expiresOn;

    String accessToken() {
        return accessToken;
    }

    String tokenType() {
        return tokenType;
    }

    OffsetDateTime expiresOn() {
        return epoch.plusSeconds(Integer.parseInt(this.expiresOn));
    }

    boolean isExpired() {
        OffsetDateTime now = OffsetDateTime.now();
        return now.plusMinutes(5).isAfter(expiresOn());
    }
}
