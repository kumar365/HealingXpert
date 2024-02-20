package com.fossgen.healthcare.AidXpert.dto;

import java.util.List;

import lombok.Value;

@Value
public class UserInfo {
	private Long id;
	private String displayName;
	private String email;
	private String password;
	private List<String> roles;
	private String oldPassword;
	private String newPassword;
}