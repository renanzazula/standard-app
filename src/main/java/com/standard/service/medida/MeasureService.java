package com.standard.service.medida;

import com.standard.domain.Measure;
import com.standard.domain.Produto;

import java.util.List;

public interface MeasureService {

	Measure save(Measure objct);

	Measure update(Long id, Measure objct);

	void delete(Long id);

	List<Measure> findAll();

	Measure findById(Long id);



	List<Measure> findByCategorySubcategoryBrand(Produto produto);

}
