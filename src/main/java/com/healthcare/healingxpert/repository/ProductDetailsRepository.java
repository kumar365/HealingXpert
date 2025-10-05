package com.healthcare.healingxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.healingxpert.model.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
	
}