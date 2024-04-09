package com.standard.service.brand;

import com.standard.domain.Brand;

import java.util.List;

public interface BrandService {

	Brand create(Brand objct);

	Brand update(Long id, Brand objct);

	void delete(Long id);

	List<Brand> findAll();

	Brand findById(Long id);
}
