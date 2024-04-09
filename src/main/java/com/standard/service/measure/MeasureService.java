package com.standard.service.measure;

import com.standard.domain.Measure;
import com.standard.domain.Product;

import java.util.List;

public interface MeasureService {

	Measure create(Measure objct);

	Measure update(Long id, Measure objct);

	void delete(Long id);

	List<Measure> findAll();

	Measure findById(Long id);

	List<Measure> findByCategorySubcategoryBrand(Product product);

}
