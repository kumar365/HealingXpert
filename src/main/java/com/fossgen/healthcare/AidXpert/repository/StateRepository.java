package com.fossgen.healthcare.AidXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fossgen.healthcare.AidXpert.model.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	@Query("SELECT S FROM State S WHERE S.countryId = ?1")
	List<State> findStatesByCountryId(Integer countryId);
}