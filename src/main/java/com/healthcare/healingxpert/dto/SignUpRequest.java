package com.healthcare.healingxpert.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.healthcare.healingxpert.enums.SocialProvider;

import lombok.Data;

@Data
public class SignUpRequest {
    private Long userID;

    @NotEmpty
    private String providerUserId;

    @NotEmpty
    private String displayName;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String email;

    private String password;

    private String phoneNumber;

    private SocialProvider socialProvider;

    public SignUpRequest(String providerUserId, String displayName, String email, String password, String phoneNumber) {
        this.providerUserId = providerUserId;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String providerUserId;
        private String displayName;
        private String email;
        private String password;
        private String phoneNumber;
        private SocialProvider socialProvider;

        public Builder addProviderUserID(String providerUserId) {
            this.providerUserId = providerUserId;
            return this;
        }

        public Builder addDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder addEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder addPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder addPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder addSocialProvider(SocialProvider socialProvider) {
            this.socialProvider = socialProvider;
            return this;
        }

        public SignUpRequest build() {
            SignUpRequest signUpRequest = new SignUpRequest(providerUserId, displayName, email, password, phoneNumber);
            signUpRequest.setSocialProvider(socialProvider);
            return signUpRequest;
        }
    }
}
