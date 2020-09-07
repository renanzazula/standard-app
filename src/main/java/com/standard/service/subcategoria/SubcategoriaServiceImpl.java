package com.standard.service.subcategoria;

import com.standard.domain.Subcategoria;
import com.standard.entity.SubcategoriaEntity;
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
	public Subcategoria incluir(Subcategoria entity) {
		SubcategoriaEntity subcategoriaDB = new SubcategoriaEntity();
		subcategoriaDB.setNome(entity.getNome());
		subcategoriaDB.setDescricao(entity.getDescricao());
		return JpaFunctions.subcategoriaToSubCategoriaEntity.apply(repository.saveAndFlush(subcategoriaDB));
	}

	@Override
	@Transactional
	public Subcategoria alterar(Long codigo, Subcategoria entity) {
		SubcategoriaEntity subcategoriaDB = repository.findById(entity.getCodigo()).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		Objects.requireNonNull(subcategoriaDB).setDescricao(entity.getDescricao());
		subcategoriaDB.setNome(entity.getNome());
		return JpaFunctions.subcategoriaToSubCategoriaEntity.apply(repository.saveAndFlush(subcategoriaDB));
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		SubcategoriaEntity subcategoriaDB = repository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		if(subcategoriaDB != null){
			subcategoriaDB.setStatus(StatusEnum.INATIVO);
		}
		repository.save(subcategoriaDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "subcategoriaListCache", condition = "#showInventoryOnHand == false")
	public List<Subcategoria> consultar() {
		return repository.findAll().stream().map(JpaFunctions.subcategoriaToSubCategoriaEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "subcategoriaCache", key = "#codigo", condition = "#showInventoryOnHand == false")
	public Subcategoria consultarByCodigo(Long codigo) {
		return JpaFunctions.subcategoriaToSubCategoriaEntity.apply(repository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

}
