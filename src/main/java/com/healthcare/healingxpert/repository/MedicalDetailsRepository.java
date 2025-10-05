package com.healthcare.healingxpert.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.healingxpert.model.MedicalDetails;

@Repository
public interface MedicalDetailsRepository extends JpaRepository<MedicalDetails, Long> {
    @Query("SELECT md FROM MedicalDetails md WHERE md.userId = ?1")
    List<MedicalDetails> findMedicalDetailsByUserId(Long userId);
}
