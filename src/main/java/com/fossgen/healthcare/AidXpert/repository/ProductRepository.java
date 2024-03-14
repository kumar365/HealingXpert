package com.fossgen.healthcare.AidXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fossgen.healthcare.AidXpert.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}