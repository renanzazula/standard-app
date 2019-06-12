package com.standard.service.venda;

import com.standard.domain.Venda;

import java.util.List;

public interface VendaService {
	Venda incluir(Venda venda);

	Venda alterar(Venda venda);

	Venda alterarStatusVendaParaEfetuada(Venda venda);
	Venda alterarStatusVendaParaNaoRealizada(Venda venda);

	void cancelar(Venda venda);

	Venda consultarByCodigo(Venda venda);

	List<Venda> consultar();

	List<Venda> filtrarVenda(Venda venda);

	 
}
