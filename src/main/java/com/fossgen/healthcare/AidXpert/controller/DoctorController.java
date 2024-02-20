package com.fossgen.healthcare.AidXpert.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fossgen.healthcare.AidXpert.model.Appointment;
import com.fossgen.healthcare.AidXpert.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/doctors")
public class DoctorController {


	@Autowired
	private AppointmentService appointmentService;

	@GetMapping(path = "/doctorAppointments/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> doctorAppointments(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long id) {
		log.info("In side doctorAppointments()");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String doctorName = authentication.getName();
		List<Appointment> doctorAppointments = appointmentService.findByDoctorName(doctorName);
		return ResponseEntity.status(HttpStatus.OK).body(doctorAppointments);
	}

}
