package com.standard.service.FormaDePagamento;

import java.util.List;

import com.standard.domain.FormasDePagamento;

public interface FormaDePagamentoService {

	FormasDePagamento incluir(FormasDePagamento objct);

	FormasDePagamento alterar(Integer codigo, FormasDePagamento objct);
	
	void excluir(Integer codigo);
	
	List<FormasDePagamento> consultar();
	
	FormasDePagamento consultarByCodigo(Integer codigo);
	
}
