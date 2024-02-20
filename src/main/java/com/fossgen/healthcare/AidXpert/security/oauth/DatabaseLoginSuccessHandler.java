//package com.fossgen.healthcare.AidXpert.security.oauth;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import com.fossgen.healthcare.AidXpert.service.UserService1;
//
//
//@Component
//public class DatabaseLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//	@Autowired UserService1 userService;
//	
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws ServletException, IOException {
//		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
//		userService.updateAuthenticationType(userDetails.getUsername(), "database");
//		super.onAuthenticationSuccess(request, response, authentication);
//	}
//
//}
