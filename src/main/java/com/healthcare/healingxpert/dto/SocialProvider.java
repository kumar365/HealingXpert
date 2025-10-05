package com.healthcare.healingxpert.dto;

/**
 * Supported OAuth2 providers
 */
public enum SocialProvider {
    GOOGLE("google"),
    FACEBOOK("facebook"),
    LOCAL("local");

    private String providerType;

    SocialProvider(String providerType) {
        this.providerType = providerType;
    }

    public String getProviderType() {
        return providerType;
    }
}
