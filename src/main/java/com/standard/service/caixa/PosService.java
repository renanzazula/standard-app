package com.standard.service.caixa;

import com.standard.domain.Pos;
import com.standard.domain.Venda;
import com.standard.entity.PosEntity;

public interface PosService {

	Pos carregarCaixa(Pos pos);

	Pos openPos(Pos pos);

	Pos closePos(Pos pos);

	Pos getLastPos();

	Pos getPos(Pos pos);

	Long generateLastPosId();

	Pos updateAmountPos(PosEntity pos, Venda venda);
}
