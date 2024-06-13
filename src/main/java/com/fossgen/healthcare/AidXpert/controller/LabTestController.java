package com.fossgen.healthcare.AidXpert.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fossgen.healthcare.AidXpert.Util.ImageUtils;
import com.fossgen.healthcare.AidXpert.model.MessageResponse;
import com.fossgen.healthcare.AidXpert.model.TestDetails;
import com.fossgen.healthcare.AidXpert.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/labTest")
public class LabTestController {

	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/addTestDetails", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addTestDetails(@RequestPart(name = "testDetails") TestDetails testDetails,
			@RequestParam("file") List<MultipartFile> fileList) {
		log.info("Inside addTestDetails start");
		if (null != fileList && fileList.size() > 0) {
			for (MultipartFile file : fileList) {
				try {
					testDetails.setImageData(ImageUtils.compressImage(file.getBytes()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		testDetails.setIpAddress("");
		commonService.saveTestDetails(testDetails);
		log.info("Inside addTestDetails end");
		return ResponseEntity.ok().body(new MessageResponse("Test Details added successfully!"));
	}

	@GetMapping("/getTestDetailsById/{id}")
	public TestDetails getTestDetailsById(@PathVariable int id) {
		log.info("Inside getTestDetailsById start");
		TestDetails testDetails = commonService.getTestDetailsById(id);
		try {
			
			if (null != testDetails && null != testDetails.getImageData()) {
				testDetails.setImageData(ImageUtils.decompressImage(testDetails.getImageData()));
			}
		} catch (Exception e) {
			log.error("Inside getTestDetailsById() error::" + e);
		}
		log.debug("Inside getTestDetailsById id::" + id);
		return testDetails;
	}

	@DeleteMapping("/deleteTestDetailsById/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		log.info("Inside delete start");
		commonService.deleteTestDetailsById(id);
		return new ResponseEntity<String>("Test Details is deleted successfully", HttpStatus.OK);
	}
}
