package com.fossgen.healthcare.AidXpert.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fossgen.healthcare.AidXpert.dto.LocalUser;
import com.fossgen.healthcare.AidXpert.dto.UserInfo;
import com.fossgen.healthcare.AidXpert.enums.SocialProvider;
import com.fossgen.healthcare.AidXpert.model.Role;
import com.fossgen.healthcare.AidXpert.model.User;

/**
 * @author KUMAR
 */
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
		List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		User user = localUser.getUser();
		return new UserInfo(user.getId(), user.getUsername().toString(), user.getEmail(), user.getPassword(), roles,
				null, null);
	}
}
