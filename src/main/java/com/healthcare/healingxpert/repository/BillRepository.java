package com.healthcare.healingxpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.healingxpert.model.Bill;

public interface BillRepository extends JpaRepository<Bill,String> {

	List<Bill> findByName(String name);
}
