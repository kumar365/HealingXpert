package com.healthcare.healingxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.healingxpert.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

}