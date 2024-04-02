package com.standard.service.categoria;

import com.standard.domain.Category;

import java.util.List;

public interface CategoryService {

	Category save(Category entity);

	Category update(Long codigo, Category entity);
 
	Category findById(Long codigo);
	
	List<Category> findAll();

	void delete(Long codigo);
}
