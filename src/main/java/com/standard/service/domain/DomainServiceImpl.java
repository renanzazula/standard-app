package com.standard.service.domain;

import com.standard.domain.Domain;
import com.standard.entity.DomainEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.DomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DomainServiceImpl implements DomainService {

	private final DomainRepository domainRepository;

	@Override
	@Transactional
	public Domain create(Domain entity) {
		DomainEntity domainDB = new DomainEntity();
		domainDB.setDescription(entity.getDescription());
		domainDB.setName(entity.getName());
		return JpaFunctions.domainToDomainEntity.apply(domainRepository.save(domainDB));
	}

	@Override
	@Transactional
	public Domain update(Long id, Domain domain) {
		DomainEntity domainDB = domainRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		Objects.requireNonNull(domainDB).setDescription(domain.getDescription());
		domainDB.setName(domain.getName());
		return JpaFunctions.domainToDomainEntity.apply(domainRepository.save(domainDB));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		DomainEntity domainDB = domainRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!"));
		if(domainDB != null) {
			domainDB.setStatus(StatusEnum.INATIVO);
		}
		domainRepository.save(domainDB);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "domainListCache", condition = "#showInventoryOnHand == false")
	public List<Domain> findAll() {
		return domainRepository.findAll().stream().map(JpaFunctions.domainToDomainEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "domainCache", key = "#id", condition = "#showInventoryOnHand == false")
	public Domain findById(Long id) {
		return JpaFunctions.domainToDomainEntity.apply(domainRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
	}

}
