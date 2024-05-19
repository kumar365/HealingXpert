package com.fossgen.healthcare.AidXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.model.ProductDetails;
import com.fossgen.healthcare.AidXpert.repository.ProductDetailsRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	public ProductDetails saveProduct(ProductDetails product) {
		product.setVersion(AppUtils.VERSION);
		product.setCreatedBy(AppUtils.getName());
		product.setCreatedDate(AppUtils.getTimestamp());
		return productDetailsRepository.save(product);
	}

	public List<ProductDetails> getProductList(long id) {
		return productDetailsRepository.findAll();
	}
}
