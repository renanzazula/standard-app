package com.standard.service.subcategory;

import com.standard.domain.Subcategory;
import com.standard.entity.SubcategoryEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.SubcategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

	private final SubcategoryRepository subcategoryRepository;

	public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
		this.subcategoryRepository = subcategoryRepository;
	}

	@Override
	@Transactional
	public Subcategory create(Subcategory entity) {
		SubcategoryEntity subcategoryDB = new SubcategoryEntity();
		subcategoryDB.setName(entity.getName());
		subcategoryDB.setDescription(entity.getDescription());
		return JpaFunctions.subcategoryToSubCategoryEntity.apply(subcategoryRepository.saveAndFlush(subcategoryDB));
	}

	@Override
	@Transactional
	public Subcategory update(Long id, Subcategory entity) {
		SubcategoryEntity subcategoryDB = subcategoryRepository.findById(entity.getId()).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		Objects.requireNonNull(subcategoryDB).setDescription(entity.getDescription());
		subcategoryDB.setName(entity.getName());
		return JpaFunctions.subcategoryToSubCategoryEntity.apply(subcategoryRepository.saveAndFlush(subcategoryDB));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		SubcategoryEntity subcategoryDB = subcategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		if(subcategoryDB != null){
			subcategoryDB.setStatus(StatusEnum.INATIVO);
		}
		subcategoryRepository.save(subcategoryDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "subcategoryListCache", condition = "#showInventoryOnHand == false")
	public List<Subcategory> findAll() {
		return subcategoryRepository.findAll().stream().map(JpaFunctions.subcategoryToSubCategoryEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "subcategoriaCache", key = "#id", condition = "#showInventoryOnHand == false")
	public Subcategory findById(Long id) {
		return JpaFunctions.subcategoryToSubCategoryEntity.apply(subcategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

}
