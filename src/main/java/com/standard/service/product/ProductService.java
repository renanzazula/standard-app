package com.standard.service.product;

import com.standard.domain.Product;

import java.util.List;

public interface ProductService {
	Product create(Product product);
	Product update(Long id, Product product);
	void delete(Long id);
	Product getById(Long id);
	List<Product> findAll();

	Product getByBarCode(String barcode);

}
