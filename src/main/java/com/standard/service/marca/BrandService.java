package com.standard.service.marca;

import com.standard.domain.Brand;

import java.util.List;

public interface BrandService {

	Brand save(Brand objct);

	Brand update(Long codigo, Brand objct);

	void delete(Long id);

	List<Brand> findAll();

	Brand findById(Long id);
}
