package com.standard.service.dominio;

import com.standard.domain.Domain;

import java.util.List;

public interface DomainService {

	Domain save(Domain objct);

	Domain update(Long codigo, Domain objct);

	void delete(Long codigo);

	List<Domain> findAll();

	Domain findById(Long codigo);
}
