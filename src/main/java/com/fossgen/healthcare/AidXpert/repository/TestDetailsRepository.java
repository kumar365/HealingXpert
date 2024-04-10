package com.fossgen.healthcare.AidXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fossgen.healthcare.AidXpert.model.TestDetails;

@Repository
public interface TestDetailsRepository extends JpaRepository<TestDetails, Integer> {

	@Query("SELECT TD FROM TestDetails TD WHERE TD.testId = ?1")
	TestDetails getTestDetailsByTestId(Integer testId);
}