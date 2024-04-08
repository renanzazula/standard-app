package com.standard.service.caixa;

import com.standard.domain.Pos;
import com.standard.domain.Venda;
import com.standard.entity.PosEntity;
import com.standard.enums.StatusPOSEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.PosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PosServiceImpl implements PosService {

	private final PosRepository repository;

	@Autowired
	public PosServiceImpl(PosRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Pos carregarCaixa(Pos pos) {
		return JpaFunctions.posToPosEntity.apply(repository.getOne(pos.getCodigo()));
	}

	@Override
	@Transactional
	public Pos openPos(Pos pos) {
		PosEntity posEntity = new PosEntity();
		posEntity.setOpenAmount(pos.getValorInicial());
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
		PosEntity posEntity = repository.getOne(pos.getCodigo());
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
			pos.setCodigo(generateLastPosId());
			pos.setDataAbertura(new Date());
			pos.setHoraAbertura(new Date());
			pos.setValorInicial((double) 0);
			pos.setValorFinal((double) 0);
			pos.setTotalVendas((double) 0);
			pos.setTotalDesconto((double) 0);
			pos.setTotal((double) 0);
			pos.setStatus(StatusPOSEnum.F.toString());
			return pos;
 		}
	}

	@Override
	@Transactional
	public Pos getPos(Pos pos) {
		return JpaFunctions.posToPosEntity.apply(repository.getOne(pos.getCodigo()));
	}

	@Override
	@Transactional
	public Pos updateAmountPos(PosEntity pos, Venda venda) {
		PosEntity posEntity = repository.getOne(pos.getId());
		
		posEntity.setTotalDiscount(posEntity.getTotalDiscount() + venda.getDesconto() );
		Double totalVendas = posEntity.getTotalOrders() + venda.getValorPago();
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
