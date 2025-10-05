package com.healthcare.healingxpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthcare.healingxpert.model.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	@Query("SELECT S FROM State S WHERE S.countryId = ?1")
	List<State> findStatesByCountryId(Integer countryId);
}