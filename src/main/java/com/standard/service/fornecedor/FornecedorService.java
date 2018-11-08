package com.standard.service.fornecedor;

import java.util.List;

import com.standard.domain.Fornecedor;

public interface FornecedorService {

	Fornecedor incluir(Fornecedor objct);

	Fornecedor alterar(Integer codigo,Fornecedor objct);
	
	void excluir(Integer codigo);
	
	List<Fornecedor> consultar();
	
	Fornecedor consultarByCodigo(Integer codigo);
}
