package com.standard.service.subcategoria;

import com.standard.domain.Subcategoria;
import com.standard.entity.SubCategoriaEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.SubCategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {

	private final SubCategoriaRepository repository;

	public SubCategoriaServiceImpl(SubCategoriaRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Subcategoria incluir(Subcategoria entity) {
		SubCategoriaEntity subCategoriaDB = new SubCategoriaEntity();
		subCategoriaDB.setNome(entity.getNome());
		subCategoriaDB.setDescricao(entity.getDescricao());
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.saveAndFlush(subCategoriaDB));
	}

	@Override
	@Transactional
	public Subcategoria alterar(Long codigo, Subcategoria entity) {
		SubCategoriaEntity subCategoriaDB = repository.findById(entity.getCodigo()).orElse(null);
		Objects.requireNonNull(subCategoriaDB).setDescricao(entity.getDescricao());
		subCategoriaDB.setNome(entity.getNome());
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.saveAndFlush(subCategoriaDB));
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		SubCategoriaEntity subCategoriaDB = repository.findById(codigo).orElse(null);
		repository.delete(subCategoriaDB);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subcategoria> consultar() {
		return repository.findAll().stream().map(JpaFunctions.subCategoriaToSubCategoriaEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Subcategoria consultarByCodigo(Long codigo) {
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.findById(codigo).orElse(null));
	}

}
