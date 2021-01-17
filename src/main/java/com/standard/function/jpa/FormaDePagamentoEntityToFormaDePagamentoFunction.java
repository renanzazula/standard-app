package com.standard.function.jpa;

import com.standard.domain.FormasDePagamento;
import com.standard.entity.FormaDePagamentoEntity;

import java.util.function.Function;

public class FormaDePagamentoEntityToFormaDePagamentoFunction implements Function<FormaDePagamentoEntity, FormasDePagamento> {

	@Override
	public FormasDePagamento apply(FormaDePagamentoEntity input) {
		FormasDePagamento output = new FormasDePagamento();
		if(input != null) {
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
			output.setPorcentagemDesconto(input.getPorcentagemDesconto());
			output.setData(input.getDataCriacao());
			output.setHora(input.getHoraCriacao());
		}
		return output;
	}

}
