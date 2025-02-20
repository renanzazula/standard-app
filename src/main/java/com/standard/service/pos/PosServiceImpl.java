package com.standard.service.pos;

import com.standard.domain.Order;
import com.standard.domain.Pos;
import com.standard.entity.PosEntity;
import com.standard.enums.StatusPOSEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.PosRepository;
import com.standard.security.exceptions.PosNotFoundException;
import com.standard.util.ConstantMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class PosServiceImpl implements PosService
{

	private final PosRepository posRepository;

	@Override
	@Transactional
	public Pos loadPos(Pos pos)
	{
		return JpaFunctions.posToPosEntity.apply(posRepository.findById(pos.getId()).orElseThrow(() -> new PosNotFoundException(ConstantMessage.POS_NOT_FOUND)));
	}

	@Override
	@Transactional
	public Pos openPos(Pos pos)
	{
		PosEntity posEntity = new PosEntity();
		posEntity.setOpenAmount(pos.getOpenAmount());
		posEntity.setCloseAmount((double) 0);
		posEntity.setTotal((double) 0);
		posEntity.setTotalDiscount((double) 0);
		posEntity.setTotalOrders((double) 0);
		posEntity.setStatus(StatusPOSEnum.OPEN);
		posEntity.setOpenDate(new Date());
		posEntity.setOpenTime(new Date());
		return JpaFunctions.posToPosEntity.apply(posRepository.saveAndFlush(posEntity));
	}

	@Override
	@Transactional
	public Pos closePos(Pos pos)
	{
		PosEntity posEntity = posRepository.findById(pos.getId()).orElseThrow(() -> new PosNotFoundException(ConstantMessage.POS_NOT_FOUND));
		posEntity.setStatus(StatusPOSEnum.CLOSE);
		posEntity.setCloseDate(new Date());
		posEntity.setCloseTime(new Date());
		return JpaFunctions.posToPosEntity.apply(posRepository.saveAndFlush(posEntity));
	}

	@Override
	@Transactional
	public Pos getLastPos()
	{
		PosEntity posEntity = posRepository.getLastPos();
		if (posEntity != null) {
			return JpaFunctions.posToPosEntity.apply(posEntity);
		} else {
			Pos pos = new Pos();
			pos.setId(getLastPosId());
			pos.setOpenDate(LocalDate.now());
			pos.setOpenTime(OffsetDateTime.now());
			pos.setOpenAmount((double) 0);
			pos.setCloseAmount((double) 0);
			pos.setTotalOrders((double) 0);
			pos.setTotalDiscount((double) 0);
			pos.setTotal((double) 0);
			pos.setStatus(StatusPOSEnum.CLOSE.toString());
			return pos;
		}
	}

	@Override
	@Transactional
	public Pos getPos(Pos pos)
	{
		return JpaFunctions.posToPosEntity.apply(posRepository.findById(pos.getId()).orElseThrow(() -> new PosNotFoundException(ConstantMessage.POS_NOT_FOUND)));
	}

	@Override
	@Transactional
	public Pos updateAmountPos(PosEntity pos, Order order)
	{
		PosEntity posEntity = posRepository.findById(pos.getId()).orElseThrow(() -> new PosNotFoundException(ConstantMessage.POS_NOT_FOUND));
		posEntity.setTotalDiscount(posEntity.getTotalDiscount() + order.getDiscount());
		Double totalOrder = posEntity.getTotalOrders() + order.getPaidAmount();
		posEntity.setTotalOrders(totalOrder);

		if (pos.getOpenAmount() == null) {
			posEntity.setTotal(totalOrder + 0);
		} else {
			posEntity.setTotal(totalOrder + pos.getOpenAmount());
		}

		return JpaFunctions.posToPosEntity.apply(posRepository.saveAndFlush(posEntity));
	}

	@Override
	public Long getLastPosId()
	{
		Long id = posRepository.getLastPosId();
		if (id != null) {
			id = id + 1;
		} else {
			id = 0L;
		}
		return id;
	}

}
