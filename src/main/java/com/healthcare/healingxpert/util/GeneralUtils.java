package com.healthcare.healingxpert.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.healthcare.healingxpert.dto.LocalUser;
import com.healthcare.healingxpert.dto.UserInfo;
import com.healthcare.healingxpert.enums.SocialProvider;
import com.healthcare.healingxpert.model.Role;
import com.healthcare.healingxpert.model.User;

public class GeneralUtils {
    private GeneralUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
        }
        return authorities;
    }

    public static SocialProvider toSocialProvider(String providerId) {
        for (SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.getProviderType().equals(providerId)) {
                return socialProvider;
            }
        }
        return SocialProvider.LOCAL;
    }

    public static UserInfo buildUserInfo(LocalUser localUser) {
        User user = localUser.getUser();
        return new UserInfo(
            user.getId(),
            user.getDisplayName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getRoles(),
            user.isEnabled(),
            user.getGender(),
            user.getDateOfBirth(),
            user.getCreatedDate(),
            user.getModifiedDate(),
            user.getCreatedBy(),
            user.getModifiedBy(),
            user.getIpAddress(),
            null  // newPassword
        );
    }
}
