package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.CaixaEntity;
import com.standard.domain.Caixa;

public class CaixaEntityToCaixaFunction implements Function<CaixaEntity, Caixa> {

	@Override
	public Caixa apply(CaixaEntity input) {
		Caixa output = new Caixa();		
		output.setCodigo(input.getCodigo());
		output.setDataAbertura(input.getDataAbertura());
		output.setHoraAbertura(input.getHoraAbertura());		
		output.setDataFechamento(input.getDataFechamento());
		output.setHoraFechamento(input.getHoraFechamento());		
		output.setValorInicial(input.getValorInicial());
		output.setValorFinal(input.getValorFinal());
		output.setTotal(input.getTotal());
		output.setTotalVendas(input.getTotalVendas());
		output.setTotalDesconto(input.getTotalDesconto());
		output.setStatus(input.getStatus().name());		
		return output;
	}

}
