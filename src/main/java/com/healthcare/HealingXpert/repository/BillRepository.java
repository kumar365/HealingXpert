package com.healthcare.HealingXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HealingXpert.model.Bill;

public interface BillRepository extends JpaRepository<Bill,String> {

	List<Bill> findByName(String name);
}
