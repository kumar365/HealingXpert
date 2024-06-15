//package com.fossgen.healthcare.AidXpert.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.fossgen.healthcare.AidXpert.model.User;
//import com.fossgen.healthcare.AidXpert.repository.UserRepository;
//import com.fossgen.healthcare.AidXpert.service.UserDetailsImpl;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	UserRepository userRepository;
//
//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//		return UserDetailsImpl.build(user);
//	}
//
//}
package com.healthcare.HealingXpert.config;


