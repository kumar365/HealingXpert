package com.healthcare.healingxpert.exception;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Pradeep kumar
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", e.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(HealingXpertException.class)
    public String customErrorHandler(Model model, HealingXpertException e) {
        model.addAttribute("errorMsg", e.getErrorMsg());
        return "error";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleInvalidRequest(BadCredentialsException e) {
        Map<String, String> body = Collections.singletonMap("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<Object> handleInvalidAuthentication(InvalidAuthenticationException e) {
        Map<String, String> body = Collections.singletonMap("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
}
