package com.healthcare.HealingXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.HealingXpert.model.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
	
}