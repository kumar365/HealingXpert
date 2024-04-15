package com.fossgen.healthcare.AidXpert.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Value;

@Value
public class FileUploadRequest {
	private MultipartFile image;
    private String title;
    private String description;
}