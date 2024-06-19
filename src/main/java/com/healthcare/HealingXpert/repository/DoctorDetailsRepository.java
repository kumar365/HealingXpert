package com.healthcare.HealingXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HealingXpert.model.DoctorDetails;

public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Long> {

}