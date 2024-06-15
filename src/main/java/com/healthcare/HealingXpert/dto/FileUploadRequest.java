package com.healthcare.HealingXpert.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Value;

@Value
public class FileUploadRequest {
	private MultipartFile image;
    private String title;
    private String description;
}