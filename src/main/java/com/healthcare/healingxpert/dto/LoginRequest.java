package com.healthcare.healingxpert.dto;

import lombok.Data;

@Data
public class LoginRequest {
	
	private String email;
	
	private String phoneNumber;

	//@NotBlank
	private String password;
}