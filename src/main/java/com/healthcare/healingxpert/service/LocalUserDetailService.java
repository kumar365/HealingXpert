package com.healthcare.healingxpert.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.healingxpert.dto.LocalUser;
import com.healthcare.healingxpert.model.User;
import com.healthcare.healingxpert.util.GeneralUtils;

import lombok.RequiredArgsConstructor;

@Service("localUserDetailService")
@RequiredArgsConstructor
public class LocalUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public LocalUser loadUserByUsername(final String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }
        return createLocalUser(user);
    }

    @Transactional(readOnly = true)
    public LocalUser loadUserById(Long id) {
        User user = userService.findUserById(id);
        return createLocalUser(user);
    }

    private LocalUser createLocalUser(User user) {
        return new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true,
            GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
    }
}
