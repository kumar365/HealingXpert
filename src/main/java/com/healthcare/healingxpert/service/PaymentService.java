package com.healthcare.healingxpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.healingxpert.util.AppUtils;
import com.healthcare.healingxpert.model.Bill;
import com.healthcare.healingxpert.model.Invoice;
import com.healthcare.healingxpert.repository.BillRepository;
import com.healthcare.healingxpert.repository.InvoiceRepository;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private BillRepository billRepository;

	public Invoice saveInvoice(Invoice invoice) {
		invoice.setPatientName(AppUtils.getName());
		invoice.setVersion(AppUtils.VERSION);
		invoice.setCreatedBy(AppUtils.getName());
		invoice.setCreatedDate(AppUtils.getTimestamp());
		return invoiceRepository.save(invoice);
	}

	public List<Invoice> findInvoicesByDoctorId(Long id) {
		return invoiceRepository.findInvoicesByDoctorId(id);
	}

	public Optional<Invoice> findInvoiceById(Integer id) {
		return invoiceRepository.findById(id);
	}

	public Bill saveBill(Bill bill) {
		bill.setName(AppUtils.getName());
		bill.setVersion(AppUtils.VERSION);
		bill.setCreatedBy(AppUtils.getName());
		bill.setCreatedDate(AppUtils.getTimestamp());
		return billRepository.save(bill);
	}

	public List<Bill> findByName(String name) {
		return billRepository.findByName(name);
	}
}
