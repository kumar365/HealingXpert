package com.fossgen.healthcare.AidXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fossgen.healthcare.AidXpert.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}