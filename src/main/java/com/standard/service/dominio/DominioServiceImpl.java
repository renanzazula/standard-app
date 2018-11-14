package com.standard.service.dominio;

import com.standard.domain.Dominio;
import com.standard.entity.DominioEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.DominioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DominioServiceImpl implements DominioService {

	private DominioRepository repository;

	public DominioServiceImpl(DominioRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Dominio incluir(Dominio entity) {
		DominioEntity dominioDB = new DominioEntity();
		dominioDB.setDescricao(entity.getDescricao());
		dominioDB.setNome(entity.getNome());
		return JpaFunctions.dominioToDominioEntity.apply(repository.save(dominioDB));
	}

	@Override
	@Transactional
	public Dominio alterar(Long codigo, Dominio dominio) {
		DominioEntity dominioDB = repository.findById(codigo).orElse(null);
		dominioDB.setDescricao(dominio.getDescricao());
		dominioDB.setNome(dominio.getNome());
		return JpaFunctions.dominioToDominioEntity.apply(repository.save(dominioDB));
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		DominioEntity dominioDB = repository.findById(codigo).orElse(null);
		// dominioDB.setStatus(Status.Inativo);
		repository.delete(dominioDB);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dominio> consultar() {
		return repository.findAll().stream().map(JpaFunctions.dominioToDominioEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Dominio consultarByCodigo(Long codigo) {
		return JpaFunctions.dominioToDominioEntity.apply(repository.findById(codigo).orElse(null));
	}

}
