package com.standard.service.fornecedor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.standard.entity.FornecedorEntity;
import com.standard.function.JpaFunctions;
import com.standard.domain.Fornecedor;
import com.standard.repository.FornecedorRepository;

@Service
public class FornecedorServiceImpl implements FornecedorService {


	private FornecedorRepository repository;

	public FornecedorServiceImpl(FornecedorRepository repository) {
		this.repository = repository;
	}

	@Override
	public Fornecedor incluir(Fornecedor entity) {
		FornecedorEntity fornecedorDB = new FornecedorEntity();
		fornecedorDB.setDescricao(entity.getDescricao());
		fornecedorDB.setNome(entity.getNome());
		return JpaFunctions.fornecedortoFornecedorEntity.apply(repository.saveAndFlush(fornecedorDB));
	}

	@Override
	public Fornecedor alterar(Integer codigo, Fornecedor entity) {
		FornecedorEntity fornecedorDB = repository.getOne(codigo);
	 	fornecedorDB.setDescricao(entity.getDescricao());
		fornecedorDB.setNome(entity.getNome());
		return JpaFunctions.fornecedortoFornecedorEntity.apply(repository.saveAndFlush(fornecedorDB));
	}

	@Override
	public void excluir(Integer codigo) {
		FornecedorEntity fornecedorDB = repository.getOne(codigo);
		repository.delete(fornecedorDB);
	}

	@Override
	public List<Fornecedor> consultar() {
		return repository.findAll().stream().map(JpaFunctions.fornecedortoFornecedorEntity).collect(Collectors.toList());
	}

	@Override
	public Fornecedor consultarByCodigo(Integer codigo) {
		return JpaFunctions.fornecedortoFornecedorEntity.apply(repository.getOne(codigo));
	}

}
