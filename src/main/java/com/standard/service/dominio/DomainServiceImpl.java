package com.standard.service.dominio;

import com.standard.domain.Domain;
import com.standard.entity.DomainEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.DomainRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DomainServiceImpl implements DomainService {

	private final DomainRepository repository;

	public DomainServiceImpl(DomainRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Domain save(Domain entity) {
		DomainEntity domainDB = new DomainEntity();
		domainDB.setDescricao(entity.getDescricao());
		domainDB.setNome(entity.getNome());
		return JpaFunctions.domainToDomainEntity.apply(repository.save(domainDB));
	}

	@Override
	@Transactional
	public Domain update(Long id, Domain domain) {
		DomainEntity domainDB = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		Objects.requireNonNull(domainDB).setDescricao(domain.getDescricao());
		domainDB.setNome(domain.getNome());
		return JpaFunctions.domainToDomainEntity.apply(repository.save(domainDB));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		DomainEntity domainDB = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		if(domainDB != null) {
			domainDB.setStatus(StatusEnum.INATIVO);
		}
		repository.save(domainDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "domainListCache", condition = "#showInventoryOnHand == false")
	public List<Domain> findAll() {
		return repository.findAll().stream().map(JpaFunctions.domainToDomainEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "domainCache", key = "#id", condition = "#showInventoryOnHand == false")
	public Domain findById(Long id) {
		return JpaFunctions.domainToDomainEntity.apply(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

}
