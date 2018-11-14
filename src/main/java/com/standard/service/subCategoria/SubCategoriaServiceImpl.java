package com.standard.service.subCategoria;

import com.standard.domain.SubCategoria;
import com.standard.entity.SubCategoriaEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.SubCategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {

	private SubCategoriaRepository repository;

	public SubCategoriaServiceImpl(SubCategoriaRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public SubCategoria incluir(SubCategoria entity) {
		SubCategoriaEntity subCategoriaDB = new SubCategoriaEntity();
		subCategoriaDB.setNome(entity.getNome());
		subCategoriaDB.setDescricao(entity.getDescricao());
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.saveAndFlush(subCategoriaDB));
	}

	@Override
	@Transactional
	public SubCategoria alterar(Long codigo, SubCategoria entity) {
		SubCategoriaEntity subCategoriaDB = repository.findById(entity.getCodigo()).orElse(null);
		subCategoriaDB.setDescricao(entity.getDescricao());
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
	public List<SubCategoria> consultar() {
		return repository.findAll().stream().map(JpaFunctions.subCategoriaToSubCategoriaEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public SubCategoria consultarByCodigo(Long codigo) {
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.findById(codigo).orElse(null));
	}

}
