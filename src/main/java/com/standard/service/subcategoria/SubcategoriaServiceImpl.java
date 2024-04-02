package com.standard.service.subcategoria;

import com.standard.domain.Subcategory;
import com.standard.entity.SubcategoryEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.SubcategoriaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SubcategoriaServiceImpl implements SubcategoriaService {

	private final SubcategoriaRepository repository;

	public SubcategoriaServiceImpl(SubcategoriaRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Subcategory save(Subcategory entity) {
		SubcategoryEntity subcategoriaDB = new SubcategoryEntity();
		subcategoriaDB.setNome(entity.getNome());
		subcategoriaDB.setDescricao(entity.getDescricao());
		return JpaFunctions.subcategoryToSubCategoryEntity.apply(repository.saveAndFlush(subcategoriaDB));
	}

	@Override
	@Transactional
	public Subcategory update(Long codigo, Subcategory entity) {
		SubcategoryEntity subcategoriaDB = repository.findById(entity.getCodigo()).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		Objects.requireNonNull(subcategoriaDB).setDescricao(entity.getDescricao());
		subcategoriaDB.setNome(entity.getNome());
		return JpaFunctions.subcategoryToSubCategoryEntity.apply(repository.saveAndFlush(subcategoriaDB));
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
	public List<Subcategory> consultar() {
		return repository.findAll().stream().map(JpaFunctions.subcategoryToSubCategoryEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "subcategoriaCache", key = "#codigo", condition = "#showInventoryOnHand == false")
	public Subcategory consultarByCodigo(Long codigo) {
		return JpaFunctions.subcategoryToSubCategoryEntity.apply(repository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

}
