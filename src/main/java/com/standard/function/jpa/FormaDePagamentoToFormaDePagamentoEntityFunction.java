package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.FormaDePagamentoEntity;
import com.standard.domain.FormasDePagamento;

public class FormaDePagamentoToFormaDePagamentoEntityFunction implements Function<FormaDePagamentoEntity, FormasDePagamento> {

	@Override
	public FormasDePagamento apply(FormaDePagamentoEntity input) {
		FormasDePagamento output = new FormasDePagamento();
		if(input != null) {
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
			output.setPorcentagemDesconto(input.getPorcentagemDesconto());
		}
		return output;
	}

}
