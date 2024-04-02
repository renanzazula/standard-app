package com.standard.service.formaDePagamento;

import com.standard.domain.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {

	PaymentMethod save(PaymentMethod objct);

	PaymentMethod update(Long id, PaymentMethod objct);
	
	void delete(Long id);
	
	List<PaymentMethod> findAll();
	
	PaymentMethod findById(Long id);
	
}
