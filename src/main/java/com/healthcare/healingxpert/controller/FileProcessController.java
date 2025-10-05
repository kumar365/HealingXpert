package com.healthcare.healingxpert.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api/file")
public class FileProcessController {

	@PostMapping("/processFile")
	public List<Object> processFile(@RequestBody MultipartFile file) {
		String fileName = file.getOriginalFilename();
		if (fileName.toLowerCase().contains(".excel")) {
			return null;
		} else {
			return null;
		}
	}
}
