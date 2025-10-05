package com.healthcare.healingxpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthcare.healingxpert.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	@Query(value = "select * from invoice I where I.doctor_id =?1", nativeQuery = true)
	List<Invoice> findInvoicesByDoctorId(Long id);

}