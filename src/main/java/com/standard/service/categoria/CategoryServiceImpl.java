package com.standard.service.categoria;

import com.standard.domain.Category;
import com.standard.entity.CategoryEntity;
import com.standard.entity.SubcategoryEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.CategoryRepository;
import com.standard.repository.SubcategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository repository;
	private final SubcategoryRepository subcategoryRepository;

	public CategoryServiceImpl(CategoryRepository repository, SubcategoryRepository subcategoryRepository) {
		this.repository = repository;
		this.subcategoryRepository = subcategoryRepository;
	}

	@Override
	@Transactional
	public Category save(Category category) {
		CategoryEntity categoryDB = new CategoryEntity();
		categoryDB.setDescription(category.getDescription());
		categoryDB.setName(category.getName());
		if(category.getSubcategories() != null) {
			Set<SubcategoryEntity> subcategories = new HashSet<>();
			category.getSubcategories().forEach(sub -> subcategories.add(subcategoryRepository.getOne(sub.getCodigo())));
			categoryDB.setSubcategories(subcategories);
		}
		return JpaFunctions.categoryToCategoryEntity.apply(repository.saveAndFlush(categoryDB));
	}

	@Override
	@Transactional
	public Category update(Long id, Category category) {
		CategoryEntity categoryDB = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		Objects.requireNonNull(categoryDB).setDescription(category.getDescription());
		categoryDB.setName(category.getName());
		categoryDB.getSubcategories().clear();
		Set<SubcategoryEntity> subcategoriaSet = new HashSet<>();
		category.getSubcategories().forEach(sub -> subcategoriaSet.add(subcategoryRepository.getOne(sub.getCodigo())));
		categoryDB.getSubcategories().addAll(subcategoriaSet);

		return JpaFunctions.categoryToCategoryEntity.apply(repository.saveAndFlush(categoryDB));
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "categoryCache", key = "#id", condition = "#showInventoryOnHand == false")
	public Category findById(Long id) {
		return JpaFunctions.categoryToCategoryEntity.apply(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "categoryListCache", condition = "#showInventoryOnHand == false")
	public List<Category> findAll() {
		return repository.findAll().stream().map(JpaFunctions.categoryToCategoryEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void delete(Long id) {
		CategoryEntity categoryDB = repository.getOne(id);
		if(categoryDB != null) {
			categoryDB.setStatus(StatusEnum.INATIVO);
		}
		repository.saveAndFlush(categoryDB);
	}

}
