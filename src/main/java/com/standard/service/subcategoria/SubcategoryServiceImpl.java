package com.standard.service.subcategoria;

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

	private final SubcategoryRepository repository;

	public SubcategoryServiceImpl(SubcategoryRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Subcategory save(Subcategory entity) {
		SubcategoryEntity subcategoryDB = new SubcategoryEntity();
		subcategoryDB.setName(entity.getNome());
		subcategoryDB.setDescription(entity.getDescricao());
		return JpaFunctions.subcategoryToSubCategoryEntity.apply(repository.saveAndFlush(subcategoryDB));
	}

	@Override
	@Transactional
	public Subcategory update(Long id, Subcategory entity) {
		SubcategoryEntity subcategoryDB = repository.findById(entity.getCodigo()).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		Objects.requireNonNull(subcategoryDB).setDescription(entity.getDescricao());
		subcategoryDB.setName(entity.getNome());
		return JpaFunctions.subcategoryToSubCategoryEntity.apply(repository.saveAndFlush(subcategoryDB));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		SubcategoryEntity subcategoryDB = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		if(subcategoryDB != null){
			subcategoryDB.setStatus(StatusEnum.INATIVO);
		}
		repository.save(subcategoryDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "subcategoryListCache", condition = "#showInventoryOnHand == false")
	public List<Subcategory> findAll() {
		return repository.findAll().stream().map(JpaFunctions.subcategoryToSubCategoryEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "subcategoriaCache", key = "#id", condition = "#showInventoryOnHand == false")
	public Subcategory findById(Long id) {
		return JpaFunctions.subcategoryToSubCategoryEntity.apply(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

}
