package com.healthcare.HealingXpert.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.healthcare.HealingXpert.dto.LocalUser;
import com.healthcare.HealingXpert.dto.UserInfo;
import com.healthcare.HealingXpert.enums.SocialProvider;
import com.healthcare.HealingXpert.model.Role;
import com.healthcare.HealingXpert.model.User;

/**
 * @author KUMAR
 */
@Component
public class GeneralUtils {

	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName().name()));
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
		List<String> roles = new ArrayList<String>();
		User user = null;
		UserInfo userInfo = null;
		if (null != localUser) {
			roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
			user = localUser.getUser();
			userInfo = new UserInfo(user.getId(), user.getUsername().toString(), user.getEmail(), user.getPassword(),
					roles, null, null);
		}
		return userInfo;
	}
}
