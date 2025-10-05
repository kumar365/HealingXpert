package com.healthcare.healingxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.healingxpert.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}