package com.fossgen.healthcare.AidXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fossgen.healthcare.AidXpert.model.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
	
	List<Invoice> findByPatientName(String patientName);

}