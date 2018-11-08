package com.standard.service.subCategoria;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.standard.entity.SubCategoriaEntity;
import com.standard.function.JpaFunctions;
import com.standard.domain.SubCategoria;
import com.standard.repository.SubCategoriaRepository;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {

	@Autowired
	private SubCategoriaRepository repository;

	@Override
	public SubCategoria incluir(SubCategoria entity) {
		SubCategoriaEntity subCategoriaDB = new SubCategoriaEntity();
		subCategoriaDB.setNome(entity.getNome());
		subCategoriaDB.setDescricao(entity.getDescricao());
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.saveAndFlush(subCategoriaDB));
	}

	@Override
	public SubCategoria alterar(Integer codigo, SubCategoria entity) {
		SubCategoriaEntity subCategoriaDB = repository.getOne(entity.getCodigo());
		subCategoriaDB.setDescricao(entity.getDescricao());
		subCategoriaDB.setNome(entity.getNome());
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.saveAndFlush(subCategoriaDB));
	}

	@Override
	public void excluir(Integer codigo) {
		SubCategoriaEntity subCategoriaDB = repository.getOne(codigo);
		repository.delete(subCategoriaDB);
	}

	@Override
	public List<SubCategoria> consultar() {
		return repository.findAll().stream().map(JpaFunctions.subCategoriaToSubCategoriaEntity).collect(Collectors.toList());
	}

	@Override
	public SubCategoria consultarByCodigo(Integer codigo) {
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.getOne(codigo));
	}

}
