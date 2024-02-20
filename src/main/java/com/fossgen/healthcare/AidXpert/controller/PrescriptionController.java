package com.fossgen.healthcare.AidXpert.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.dto.ApiResponse;
import com.fossgen.healthcare.AidXpert.exception.DependentAlreadyExistException;
import com.fossgen.healthcare.AidXpert.model.Prescription;
import com.fossgen.healthcare.AidXpert.service.AppointmentService;
import com.fossgen.healthcare.AidXpert.service.PrescriptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/prescription")
public class PrescriptionController {

	@Autowired
	private PrescriptionService prescriptionService;

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping(value = "/addPrescription")
	public ResponseEntity<?> addPrescription(@RequestBody Prescription prescription, HttpServletRequest request) {
		log.info("In side addPrescription()");
		try {
			prescription.setIpAddress(AppUtils.getClientIP(request));
			prescription = prescriptionService.save(prescription);
			Integer id = prescription.getAppointmentID();
			appointmentService.setPrescription("prescribed", id);
		} catch (DependentAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Prescription already exist!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Prescription details added successfully!"));
	}

	@GetMapping(path = "/prescriptions/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Prescription>> getPrescriptions(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long id) {
		log.info("In side getPrescriptions()");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String patientName = auth.getName();
		List<Prescription> prescriptions = prescriptionService.findByPatientName(patientName);
		return ResponseEntity.status(HttpStatus.OK).body(prescriptions);
	}
}