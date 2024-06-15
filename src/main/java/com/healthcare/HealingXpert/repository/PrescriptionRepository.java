package com.healthcare.HealingXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HealingXpert.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription,String> {

	List<Prescription> findByPatientName(String patientName);
}
