package com.standard.service.category;

import com.standard.domain.Category;

import java.util.List;

public interface CategoryService {

	Category create(Category obj);

	Category update(Long id, Category obj);
 
	Category findById(Long id);
	
	List<Category> findAll();

	void delete(Long id);
}
