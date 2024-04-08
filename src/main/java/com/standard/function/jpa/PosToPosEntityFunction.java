package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.PosEntity;
import com.standard.domain.Pos;

public class PosToPosEntityFunction implements Function<PosEntity, Pos> {

	@Override
	public Pos apply(PosEntity input) {
		Pos output = new Pos();
		output.setCodigo(input.getId());
		output.setDataAbertura(input.getOpenDate());
		output.setHoraAbertura(input.getOpenTime());
		output.setDataFechamento(input.getCloseDate());
		output.setHoraFechamento(input.getCloseTime());
		output.setValorInicial(input.getOpenAmount());
		output.setValorFinal(input.getCloseAmount());
		output.setTotal(input.getTotal());
		output.setTotalVendas(input.getTotalOrders());
		output.setTotalDesconto(input.getTotalDiscount());
		output.setStatus(input.getStatus().name());		
		return output;
	}

}
