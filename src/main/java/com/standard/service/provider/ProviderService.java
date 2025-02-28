package com.standard.service.provider;

import com.standard.domain.Provider;

import java.util.List;

public interface ProviderService {

	Provider create(Provider objct);

	Provider update(Long id, Provider objct);
	
	void delete(Long id);
	
	List<Provider> findAll();
	
	Provider findById(Long id);
}
