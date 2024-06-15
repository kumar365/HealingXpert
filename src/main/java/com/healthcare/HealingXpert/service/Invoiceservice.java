package com.healthcare.HealingXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.HealingXpert.model.Invoice;
import com.healthcare.HealingXpert.repository.InvoiceRepository;

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
