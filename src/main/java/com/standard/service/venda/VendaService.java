package com.standard.service.venda;

import java.util.List;

import com.standard.domain.Venda;

public interface VendaService {
	Venda incluir(Venda venda);

	Venda alterar(Venda venda);

	void cancelar(Venda venda);

	Venda consultarByCodigo(Venda venda);

	List<Venda> consultar();

	List<Venda> filtrarVenda(Venda venda);

	 
}
