package com.fossgen.healthcare.AidXpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fossgen.healthcare.AidXpert.model.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
	
}