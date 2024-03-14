package com.fossgen.healthcare.AidXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.model.Product;
import com.fossgen.healthcare.AidXpert.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		product.setVersion(AppUtils.VERSION);
		product.setCreatedBy(AppUtils.getName());
		product.setCreatedDate(AppUtils.getTimestamp());
		return productRepository.save(product);
	}

	public List<Product> getProductList(long id) {
		return productRepository.findAll();
	}
}
