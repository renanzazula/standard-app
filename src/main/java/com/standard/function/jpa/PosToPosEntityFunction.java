package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.PosEntity;
import com.standard.domain.Pos;

public class PosToPosEntityFunction implements Function<PosEntity, Pos> {

	@Override
	public Pos apply(PosEntity input) {
		Pos output = new Pos();
		output.setCodigo(input.getId());
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
