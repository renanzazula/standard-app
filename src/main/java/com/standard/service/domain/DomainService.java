package com.standard.service.domain;

import com.standard.domain.Domain;

import java.util.List;

public interface DomainService {

	Domain create(Domain objct);

	Domain update(Long id, Domain objct);

	void delete(Long id);

	List<Domain> findAll();

	Domain findById(Long id);
}
