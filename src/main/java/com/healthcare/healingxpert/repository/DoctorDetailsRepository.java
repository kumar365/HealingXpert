package com.healthcare.healingxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.healingxpert.model.DoctorDetails;

public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Long> {

}