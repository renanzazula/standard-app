package com.standard.service.subcategory;

import com.standard.domain.Subcategory;

import java.util.List;

public interface SubcategoryService {

	Subcategory create(Subcategory objc);

	Subcategory update(Long id, Subcategory objc);

	void delete(Long id);
	
	List<Subcategory> findAll();

	Subcategory findById(Long id);
	
}
