package com.standard.service.formaDePagamento;

import com.standard.domain.FormasDePagamento;

import java.util.List;

public interface FormaDePagamentoService {

	FormasDePagamento incluir(FormasDePagamento objct);

	FormasDePagamento alterar(Long codigo, FormasDePagamento objct);
	
	void excluir(Long codigo);
	
	List<FormasDePagamento> consultar();
	
	FormasDePagamento consultarByCodigo(Long codigo);
	
}
