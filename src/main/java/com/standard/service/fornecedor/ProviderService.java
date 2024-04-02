package com.standard.service.fornecedor;

import com.standard.domain.Provider;

import java.util.List;

public interface ProviderService {

	Provider save(Provider objct);

	Provider update(Long codigo, Provider objct);
	
	void delete(Long codigo);
	
	List<Provider> findAll();
	
	Provider findById(Long codigo);
}
