package com.fossgen.healthcare.AidXpert.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.fossgen.healthcare.AidXpert.model.Appointment;
import com.fossgen.healthcare.AidXpert.model.Product;
import com.fossgen.healthcare.AidXpert.service.AppointmentService;
import com.fossgen.healthcare.AidXpert.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private ProductService productService;

	@PostMapping(value = "/addAppointment")
	public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment, HttpServletRequest request) {
		log.info("In side addAppointment()");
		try {
			appointment.setIpAddress(AppUtils.getClientIP(request));
			appointment = appointmentService.save(appointment);
		} catch (DependentAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Appointment already exist!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Appointment details added successfully!"));
	}

	@GetMapping(path = "/patientAppointments/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> patientAppointments(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long id) {
		log.info("In side patientAppointments()");
		List<Appointment> listAppointments = appointmentService.findAppointmentsByPatientId(id);
		return ResponseEntity.status(HttpStatus.OK).body(listAppointments);
	}

	@GetMapping(path = "/doctorAppointments/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> doctorAppointments(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long id) {
		log.info("In side doctorAppointments()");
		List<Appointment> doctorAppointments = appointmentService.findAppointmentsByDoctorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(doctorAppointments);
	}
	@GetMapping(path = "/doctorAppointmentsToday/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> doctorAppointmentsToday(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long id) {
		log.info("In side doctorAppointmentsToday()");
		List<Appointment> doctorAppointments = appointmentService.findTodayAppointmentsByDoctorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(doctorAppointments);
	}

	@PostMapping(value = "/cancelAppointment")
	public ResponseEntity<?> cancelAppointment(@RequestBody Appointment appointment, HttpServletRequest request) {
		log.info("In side cancelAppointment()");
		try {
			appointment.setIpAddress(AppUtils.getClientIP(request));
			appointment = appointmentService.save(appointment);

		} catch (DependentAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Appointment does not exist!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Appointment "+ appointment.getStatus()+"  successfully!"));
	}

	@PostMapping(value = "/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody Product product, HttpServletRequest request) {
		log.info("In side addProduct()");
		try {
			product.setIpAddress(AppUtils.getClientIP(request));
			product = productService.saveProduct(product);
		} catch (DependentAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Product already exist!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Product details added successfully!"));
	}

	@GetMapping(path = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getProductList(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long id) {
		log.info("In side getProductList()");
		List<Product> productList = productService.getProductList(id);
		return ResponseEntity.status(HttpStatus.OK).body(productList);
	}

}
