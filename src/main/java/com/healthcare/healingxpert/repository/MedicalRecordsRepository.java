package com.healthcare.healingxpert.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.healingxpert.model.MedicalRecords;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, Long> {
    @Query("SELECT mr FROM MedicalRecords mr WHERE mr.userId = ?1")
    List<MedicalRecords> findMedicalRecordsByUserId(Long userId);
}
