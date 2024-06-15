package com.healthcare.HealingXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthcare.HealingXpert.model.MedicalDetails;

public interface MedicalDetailsRepository extends JpaRepository<MedicalDetails, Long> {
	@Query("SELECT M FROM MedicalDetails M WHERE M.user.id= ?1")
	List<MedicalDetails> findMedicalDetailsByUserId(Long id);
}