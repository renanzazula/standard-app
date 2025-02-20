package com.standard.function.jpa;

import com.standard.domain.Pos;
import com.standard.entity.PosEntity;

import java.util.function.Function;

public class PosToPosEntityFunction implements Function<PosEntity, Pos> {

	@Override
	public Pos apply(PosEntity input) {
		Pos output = new Pos();
		output.setId(input.getId());
//		output.setOpenDate(input.getOpenDate());
//		output.setOpenTime(input.getOpenTime());
//		output.setCloseDate(input.getCloseDate());
//		output.setCloseTime(input.getCloseTime());
		output.setOpenAmount(input.getOpenAmount());
		output.setCloseAmount(input.getCloseAmount());
		output.setTotal(input.getTotal());
		output.setTotalOrders(input.getTotalOrders());
		output.setTotalDiscount(input.getTotalDiscount());
		output.setStatus(input.getStatus().name());		
		return output;
	}

}
