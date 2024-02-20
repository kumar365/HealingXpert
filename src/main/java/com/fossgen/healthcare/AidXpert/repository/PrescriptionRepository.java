package com.fossgen.healthcare.AidXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fossgen.healthcare.AidXpert.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription,String> {

	List<Prescription> findByPatientName(String patientName);
}
