package com.standard.service.brand;

import com.standard.domain.Brand;

import java.util.List;

public interface BrandService {

	Brand create(Brand brand);

	Brand update(Long id, Brand brand);

	void delete(Long id);

	List<Brand> findAll();

	Brand findById(Long id);
}
