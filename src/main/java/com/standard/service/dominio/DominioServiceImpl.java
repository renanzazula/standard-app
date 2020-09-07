package com.standard.service.dominio;

import com.standard.domain.Dominio;
import com.standard.entity.DominioEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.DominioRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DominioServiceImpl implements DominioService {

	private final DominioRepository repository;

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
		DominioEntity dominioDB = repository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		Objects.requireNonNull(dominioDB).setDescricao(dominio.getDescricao());
		dominioDB.setNome(dominio.getNome());
		return JpaFunctions.dominioToDominioEntity.apply(repository.save(dominioDB));
	}

	@Override
	@Transactional
	public void excluir(Long codigo) {
		DominioEntity dominioDB = repository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		if(dominioDB != null) {
			dominioDB.setStatus(StatusEnum.INATIVO);
		}
		repository.save(dominioDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "dominioListCache", condition = "#showInventoryOnHand == false")
	public List<Dominio> consultar() {
		return repository.findAll().stream().map(JpaFunctions.dominioToDominioEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "dominioCache", key = "#codigo", condition = "#showInventoryOnHand == false")
	public Dominio consultarByCodigo(Long codigo) {
		return JpaFunctions.dominioToDominioEntity.apply(repository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

}
