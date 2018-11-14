package com.standard.service.caixa;

import com.standard.domain.Caixa;
import com.standard.domain.Venda;
import com.standard.entity.CaixaEntity;

public interface CaixaService {

	Caixa carregarCaixa(Caixa caixa);

	Caixa abrirCaixa(Caixa Caixa);

	Caixa fecharCaixa(Caixa Caixa);

	Caixa buscarUltimoCaixa();

	Caixa buscarCaixa(Caixa Caixa);

	Long gerarCodigoCaixa();

	Caixa updateValorCaixa(CaixaEntity caixa, Venda venda);
}
