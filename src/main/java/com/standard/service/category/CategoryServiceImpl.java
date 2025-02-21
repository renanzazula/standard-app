package com.standard.service.category;

import com.standard.domain.Category;
import com.standard.entity.CategoryEntity;
import com.standard.entity.SubcategoryEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.CategoryRepository;
import com.standard.repository.SubcategoryRepository;
import com.standard.security.exceptions.CategoryNotFoundException;
import com.standard.security.exceptions.SubcategoryNotFoundException;
import com.standard.util.ConstantMessage;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService
{

	private final CategoryRepository repository;
	private final SubcategoryRepository subcategoryRepository;

	@Override
	@Transactional
	public Category create(Category obj)
	{
		CategoryEntity categoryDB = new CategoryEntity();
		categoryDB.setDescription(obj.getDescription());
		categoryDB.setName(obj.getName());
		if (obj.getSubcategories() != null) {
			Set<SubcategoryEntity> subcategories = new HashSet<>();
			obj.getSubcategories()
					.forEach(sub -> subcategories.add(subcategoryRepository.findById(sub.getId()).orElseThrow(() -> new SubcategoryNotFoundException(ConstantMessage.SUBCATEGORY_NOT_FOUND))));
			categoryDB.setSubcategories(subcategories);
		}
		return JpaFunctions.categoryToCategoryEntity.apply(repository.saveAndFlush(categoryDB));
	}

	@Override
	@Transactional
	public Category update(Long id, Category obj)
	{
		CategoryEntity categoryDB = repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.CATEGORY_NOT_FOUND));
		Objects.requireNonNull(categoryDB).setDescription(obj.getDescription());
		categoryDB.setName(obj.getName());
		categoryDB.getSubcategories().clear();
		Set<SubcategoryEntity> subcategoriaSet = new HashSet<>();
		obj.getSubcategories().forEach(sub -> subcategoriaSet.add(subcategoryRepository.findById(sub.getId()).orElseThrow(() -> new SubcategoryNotFoundException(ConstantMessage.SUBCATEGORY_NOT_FOUND))));
		categoryDB.getSubcategories().addAll(subcategoriaSet);

		return JpaFunctions.categoryToCategoryEntity.apply(repository.saveAndFlush(categoryDB));
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "categoryCache", key = "#id")
	public Category findById(Long id)
	{
		return JpaFunctions.categoryToCategoryEntity.apply(repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.CATEGORY_NOT_FOUND)));
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "categoryListCache")
	public List<Category> findAll()
	{
		return repository.findAll().stream().map(JpaFunctions.categoryToCategoryEntity).toList();
	}

	@Override
	@Transactional
	public void delete(Long id)
	{
		CategoryEntity categoryDB = repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(ConstantMessage.CATEGORY_NOT_FOUND));
		categoryDB.setStatus(StatusEnum.DISABLE);
		repository.saveAndFlush(categoryDB);
	}

}
