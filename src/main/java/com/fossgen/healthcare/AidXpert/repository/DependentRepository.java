package com.fossgen.healthcare.AidXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fossgen.healthcare.AidXpert.model.Dependent;

public interface DependentRepository extends JpaRepository<Dependent, Long> {
	@Query("SELECT D FROM Dependent D WHERE D.user.id= ?1")
	List<Dependent> findDependentSByUserId(Long id);
}