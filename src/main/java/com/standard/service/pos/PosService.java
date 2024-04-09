package com.standard.service.pos;

import com.standard.domain.Order;
import com.standard.domain.Pos;
import com.standard.entity.PosEntity;

public interface PosService {

	Pos loadPos(Pos pos);

	Pos openPos(Pos pos);

	Pos closePos(Pos pos);

	Pos getLastPos();

	Pos getPos(Pos pos);

	Long generateLastPosId();

	Pos updateAmountPos(PosEntity pos, Order order);
}
