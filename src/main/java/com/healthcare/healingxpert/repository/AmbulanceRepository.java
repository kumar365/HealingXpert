package com.healthcare.healingxpert.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.healingxpert.model.Ambulance;

@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {
    @Query("SELECT a FROM Ambulance a WHERE a.mobileNumber = ?1 AND a.password = ?2")
    List<Ambulance> getAmbulanceByMobileNumberPassword(String mobileNumber, String password);
}
