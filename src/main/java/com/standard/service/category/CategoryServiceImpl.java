package com.standard.service.category;

import com.standard.domain.Category;
import com.standard.entity.CategoryEntity;
import com.standard.entity.SubcategoryEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.CategoryRepository;
import com.standard.repository.SubcategoryRepository;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository repository;
	private final SubcategoryRepository subcategoryRepository;

	@Override
	@Transactional
	public Category create(Category obj) {
		CategoryEntity categoryDB = new CategoryEntity();
		categoryDB.setDescription(obj.getDescription());
		categoryDB.setName(obj.getName());
		if(obj.getSubcategories() != null) {
			Set<SubcategoryEntity> subcategories = new HashSet<>();
			obj.getSubcategories().forEach(sub -> subcategories.add(subcategoryRepository.getOne(sub.getId())));
			categoryDB.setSubcategories(subcategories);
		}
		return JpaFunctions.categoryToCategoryEntity.apply(repository.saveAndFlush(categoryDB));
	}

	@Override
	@Transactional
	public Category update(Long id, Category obj) {
		CategoryEntity categoryDB = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		Objects.requireNonNull(categoryDB).setDescription(obj.getDescription());
		categoryDB.setName(obj.getName());
		categoryDB.getSubcategories().clear();
		Set<SubcategoryEntity> subcategoriaSet = new HashSet<>();
		obj.getSubcategories().forEach(sub -> subcategoriaSet.add(subcategoryRepository.getOne(sub.getId())));
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
