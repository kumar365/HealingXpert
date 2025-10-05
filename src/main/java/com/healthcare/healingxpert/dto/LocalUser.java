package com.healthcare.healingxpert.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LocalUser extends User implements OidcUser {
    private static final long serialVersionUID = -2845160792248762779L;
    private Map<String, Object> attributes;
    private OidcIdToken idToken;
    private OidcUserInfo userInfo;
    private com.healthcare.healingxpert.model.User user;

    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired,
            final boolean credentialsNonExpired, final boolean accountNonLocked,
            final Collection<? extends GrantedAuthority> authorities, final com.healthcare.healingxpert.model.User user) {
        super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public static LocalUser create(com.healthcare.healingxpert.model.User user, Map<String, Object> attributes,
            OidcIdToken idToken, OidcUserInfo userInfo) {
        LocalUser localUser = new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true,
                com.healthcare.healingxpert.util.GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
        localUser.setAttributes(attributes);
        localUser.setIdToken(idToken);
        localUser.setUserInfo(userInfo);
        return localUser;
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.attributes;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return this.userInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return this.idToken;
    }

    @Override
    public String getName() {
        return getUsername();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
}
