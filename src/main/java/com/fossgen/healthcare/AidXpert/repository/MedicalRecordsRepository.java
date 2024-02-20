package com.fossgen.healthcare.AidXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fossgen.healthcare.AidXpert.model.MedicalRecords;

public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, Long> {
	@Query("SELECT M FROM MedicalRecords M WHERE M.user.id= ?1")
	List<MedicalRecords> findMedicalRecordsByUserId(Long id);
}