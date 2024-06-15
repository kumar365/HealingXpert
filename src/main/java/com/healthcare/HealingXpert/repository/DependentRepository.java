package com.healthcare.HealingXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthcare.HealingXpert.model.Dependent;

public interface DependentRepository extends JpaRepository<Dependent, Long> {
	@Query("SELECT D FROM Dependent D WHERE D.user.id= ?1")
	List<Dependent> findDependentSByUserId(Long id);
}