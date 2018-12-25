package com.standard.service.caixa;

import com.standard.domain.Caixa;
import com.standard.domain.Venda;
import com.standard.entity.CaixaEntity;

public interface CaixaService {

	Caixa carregarCaixa(Caixa caixa);

	Caixa abrirCaixa(Caixa caixa);

	Caixa fecharCaixa(Caixa caixa);

	Caixa buscarUltimoCaixa();

	Caixa buscarCaixa(Caixa caixa);

	Long gerarCodigoCaixa();

	Caixa updateValorCaixa(CaixaEntity caixa, Venda venda);
}
