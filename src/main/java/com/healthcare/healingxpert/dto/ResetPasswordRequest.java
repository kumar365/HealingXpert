package com.healthcare.healingxpert.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {
	private String token;
	private String password;
}