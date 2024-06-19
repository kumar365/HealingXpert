package com.healthcare.HealingXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HealingXpert.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

}