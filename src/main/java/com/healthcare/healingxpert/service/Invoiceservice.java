package com.healthcare.healingxpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.healingxpert.model.Invoice;
import com.healthcare.healingxpert.repository.InvoiceRepository;

@Service
@Transactional
public class Invoiceservice {

	@Autowired
	private InvoiceRepository invoiceRepository;

	public void save(Invoice entity) {
		invoiceRepository.save(entity);
	}

	public List<Invoice> view() {
		return invoiceRepository.findAll();
	}
}
