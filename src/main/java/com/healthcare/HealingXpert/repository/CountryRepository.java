package com.healthcare.HealingXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HealingXpert.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}