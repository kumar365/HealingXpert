package com.healthcare.healingxpert.enums;

public enum SocialProvider {
    FACEBOOK("facebook"),
    TWITTER("twitter"),
    LINKEDIN("linkedin"),
    GOOGLE("google"),
    GITHUB("github"),
    LOCAL("local");

    private String providerType;

    SocialProvider(String providerType) {
        this.providerType = providerType;
    }

    public String getProviderType() {
        return providerType;
    }
}
