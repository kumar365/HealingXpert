package com.healthcare.HealingXpert.dto;

import lombok.Data;

@Data
public class OtpRequest {
	private String phoneNumber;
	private String email;
}