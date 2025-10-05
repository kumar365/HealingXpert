package com.healthcare.healingxpert.controller;

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

import com.healthcare.healingxpert.util.AppUtils;
import com.healthcare.healingxpert.util.ImageUtils;
import com.healthcare.healingxpert.dto.ApiResponse;
import com.healthcare.healingxpert.exception.DependentAlreadyExistException;
import com.healthcare.healingxpert.model.Bill;
import com.healthcare.healingxpert.model.Invoice;
import com.healthcare.healingxpert.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping(value = "/addInvoice")
	public ResponseEntity<?> addInvoice(@RequestBody Invoice invoice, HttpServletRequest request) {
		log.info("Inside addInvoice()");
		try {
			invoice.setIpAddress(AppUtils.getClientIP(request));
			invoice = paymentService.saveInvoice(invoice);
		} catch (DependentAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Invoice already exist!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Invoice details added successfully!"));
	}

	@GetMapping(path = "/invoices/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invoice>> getInvoices(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long id) {
		log.info("Inside getInvoices()");
		List<Invoice> invoices = paymentService.findInvoicesByDoctorId(id);
		for (int i = 0; i < invoices.size(); i++) {
			if (null != invoices.get(i) && null != invoices.get(i).getPatientUser()
					&& null != invoices.get(i).getPatientUser().getImageData()) {
				try {
					invoices.get(i).getPatientUser()
							.setImageData(ImageUtils.decompressImage(invoices.get(i).getPatientUser().getImageData()));
				} catch (Exception e) {
				}
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(invoices);
	}

	@GetMapping(path = "/invoiceById/{id}")
	public Invoice getInvoiceById(@PathVariable("id") Integer id) {
		log.info("Inside getInvoiceById()");
		Invoice invoice = paymentService.findInvoiceById(id).get();
		return invoice;
	}

	@PostMapping(value = "/addBill")
	public ResponseEntity<?> addBill(@RequestBody Bill bill, HttpServletRequest request) {
		log.info("Inside addBill()");
		try {
			bill.setIpAddress(AppUtils.getClientIP(request));
			bill = paymentService.saveBill(bill);
		} catch (DependentAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Bill already exist!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Bill details added successfully!"));
	}

	@GetMapping(path = "/bills/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bill>> getBills(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long id) {
		log.info("Inside getBills()");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		List<Bill> prescriptions = paymentService.findByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(prescriptions);
	}
}