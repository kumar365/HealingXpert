package com.fossgen.healthcare.AidXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fossgen.healthcare.AidXpert.model.Bill;

public interface BillRepository extends JpaRepository<Bill,String> {

	List<Bill> findByName(String name);
}
