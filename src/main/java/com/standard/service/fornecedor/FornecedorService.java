package com.standard.service.fornecedor;

import com.standard.domain.Fornecedor;

import java.util.List;

public interface FornecedorService {

	Fornecedor incluir(Fornecedor objct);

	Fornecedor alterar(Long codigo,Fornecedor objct);
	
	void excluir(Long codigo);
	
	List<Fornecedor> consultar();
	
	Fornecedor consultarByCodigo(Long codigo);
}
