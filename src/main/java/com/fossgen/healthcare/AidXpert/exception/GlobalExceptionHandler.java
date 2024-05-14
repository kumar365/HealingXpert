/**
 * 
 */
package com.fossgen.healthcare.AidXpert.exception;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Pradeep kumar
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Model model, Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorMsg", e.getMessage());
		mav.setViewName("response_redirect");
		return mav;
	}

	@ExceptionHandler(AidXpertException.class)
	public String customErrorHandler(HttpServletRequest request, Model model, AidXpertException e) {
		model.addAttribute("errorMsg", e.getErrorMsg());
		return "response_redirect";
	}

	@SuppressWarnings({ "unused", "serial" })
	@ExceptionHandler({ InvalidRequestException.class })
	public ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
		InvalidRequestException ire = (InvalidRequestException) e;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<String, Object>() {
			{
				put("message", e.getMessage());
			}
		});
	}

	@SuppressWarnings("serial")
	@ExceptionHandler(InvalidAuthenticationException.class)
	public ResponseEntity<Object> handleInvalidAuthentication(InvalidAuthenticationException e, WebRequest request) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<String, Object>() {
			{
				put("message", e.getMessage());
			}
		});
	}

}
