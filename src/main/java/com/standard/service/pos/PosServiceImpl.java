package com.standard.service.pos;

import com.standard.domain.Pos;
import com.standard.domain.Order;
import com.standard.entity.PosEntity;
import com.standard.enums.StatusPOSEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.PosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@AllArgsConstructor
public class PosServiceImpl implements PosService {

	private final PosRepository repository;

	@Override
	@Transactional
	public Pos loadPos(Pos pos) {
		return JpaFunctions.posToPosEntity.apply(repository.getOne(pos.getId()));
	}

	@Override
	@Transactional
	public Pos openPos(Pos pos) {
		PosEntity posEntity = new PosEntity();
		posEntity.setOpenAmount(pos.getOpenAmount());
		posEntity.setCloseAmount((double) 0);
		posEntity.setTotal((double) 0);
		posEntity.setTotalDiscount((double) 0);
		posEntity.setTotalOrders((double) 0);
		posEntity.setStatus(StatusPOSEnum.A);
		posEntity.setOpenDate(new Date());
		posEntity.setOpenTime(new Date());
		return  JpaFunctions.posToPosEntity.apply(repository.saveAndFlush(posEntity));
	}

	@Override
	@Transactional
	public Pos closePos(Pos pos) {
		PosEntity posEntity = repository.getOne(pos.getId());
		posEntity.setStatus(StatusPOSEnum.F);
		posEntity.setCloseDate(new Date());
		posEntity.setCloseTime(new Date());
		return  JpaFunctions.posToPosEntity.apply(repository.saveAndFlush(posEntity));
	}	

	@Override
	@Transactional
	public Pos getLastPos() {
		PosEntity posEntity = repository.getLastPos();
 		if(posEntity != null) {
 			return JpaFunctions.posToPosEntity.apply(posEntity);
 		} else {
 			Pos pos = new Pos();
			pos.setId(generateLastPosId());
			pos.setOpenDate(new Date());
			pos.setOpenTime(new Date());
			pos.setOpenAmount((double) 0);
			pos.setCloseAmount((double) 0);
			pos.setTotalOrders((double) 0);
			pos.setTotalDiscount((double) 0);
			pos.setTotal((double) 0);
			pos.setStatus(StatusPOSEnum.F.toString());
			return pos;
 		}
	}

	@Override
	@Transactional
	public Pos getPos(Pos pos) {
		return JpaFunctions.posToPosEntity.apply(repository.getOne(pos.getId()));
	}

	@Override
	@Transactional
	public Pos updateAmountPos(PosEntity pos, Order order) {
		PosEntity posEntity = repository.getOne(pos.getId());
		
		posEntity.setTotalDiscount(posEntity.getTotalDiscount() + order.getDiscount() );
		Double totalVendas = posEntity.getTotalOrders() + order.getPaidAmount();
		posEntity.setTotalOrders(totalVendas);
		

		if(pos.getOpenAmount() == null) {
			posEntity.setTotal(totalVendas + (double) 0);
		}else {
			posEntity.setTotal(totalVendas + pos.getOpenAmount());
		}
		
		return JpaFunctions.posToPosEntity.apply(repository.saveAndFlush(posEntity));
	}
		
	@Override
	@Transactional
	public Long generateLastPosId() {
		Long id = repository.generateLastPosId();
		if(id != null) {
			id = id +1;
		}else {
			id = 0L;
		}			  
		return id;
	}

}
