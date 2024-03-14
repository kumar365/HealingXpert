package com.fossgen.healthcare.AidXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fossgen.healthcare.AidXpert.model.Ambulance;

public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {
	@Query("SELECT A FROM Ambulance A WHERE A.mobileNumber = ?1 AND A.password=?2")
	List<Ambulance> getAmbulanceByMobileNumberPassword(String mobileNumber, String password);
}