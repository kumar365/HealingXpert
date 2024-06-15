package com.healthcare.HealingXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthcare.HealingXpert.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	@Query("SELECT C FROM City C WHERE C.stateId = ?1")
	List<City> findCitiesByStateId(Integer stateId);
}