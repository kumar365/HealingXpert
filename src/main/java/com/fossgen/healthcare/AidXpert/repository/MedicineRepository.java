package com.fossgen.healthcare.AidXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fossgen.healthcare.AidXpert.model.Medicine;
import com.fossgen.healthcare.AidXpert.model.User;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
	@Query("SELECT M FROM Medicine M WHERE M.name = ?1")
	List<User> getMedicineByName(String name);

}
