package com.standard.service.caixa;

import com.standard.domain.Caixa;
import com.standard.domain.Venda;
import com.standard.entity.CaixaEntity;
import com.standard.enums.StatusCaixaEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CaixaServiceImpl implements CaixaService {

	private final CaixaRepository repository;

	@Autowired
	public CaixaServiceImpl(CaixaRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Caixa carregarCaixa(Caixa caixa) {
		return JpaFunctions.caixaEntityToCaixa.apply(repository.getOne(caixa.getCodigo()));
	}

	@Override
	@Transactional
	public Caixa abrirCaixa(Caixa caixa) {
		CaixaEntity caixaEntity = new CaixaEntity();
		caixaEntity.setValorInicial(caixa.getValorInicial());
		caixaEntity.setValorFinal((double) 0);
		caixaEntity.setTotal((double) 0);
		caixaEntity.setTotalDesconto((double) 0);
		caixaEntity.setTotalVendas((double) 0);
		caixaEntity.setStatus(StatusCaixaEnum.A);
		caixaEntity.setDataAbertura(new Date());
		caixaEntity.setHoraAbertura(new Date());
		return  JpaFunctions.caixaEntityToCaixa.apply(repository.saveAndFlush(caixaEntity));
	}

	@Override
	@Transactional
	public Caixa fecharCaixa(Caixa caixa) {
		CaixaEntity caixaEntity = repository.getOne(caixa.getCodigo());
		caixaEntity.setStatus(StatusCaixaEnum.F);
		caixaEntity.setDataFechamento(new Date());
		caixaEntity.setHoraFechamento(new Date());
		return  JpaFunctions.caixaEntityToCaixa.apply(repository.saveAndFlush(caixaEntity));
	}	

	@Override
	@Transactional
	public Caixa buscarUltimoCaixa() {
		CaixaEntity caixaEntity = repository.buscarUltimoCaixa();
 		if(caixaEntity != null) {
 			return JpaFunctions.caixaEntityToCaixa.apply(caixaEntity);
 		} else {
 			Caixa caixa = new Caixa(); 
			caixa.setCodigo(gerarCodigoCaixa());
			caixa.setDataAbertura(new Date());
			caixa.setHoraAbertura(new Date());
			caixa.setValorInicial((double) 0);
			caixa.setValorFinal((double) 0);
			caixa.setTotalVendas((double) 0);
			caixa.setTotalDesconto((double) 0);
			caixa.setTotal((double) 0);
			caixa.setStatus(StatusCaixaEnum.F.toString());
			return caixa;
 		}
	}

	@Override
	@Transactional
	public Caixa buscarCaixa(Caixa caixa) {
		return JpaFunctions.caixaEntityToCaixa.apply(repository.getOne(caixa.getCodigo()));
	}

	@Override
	@Transactional
	public Caixa updateValorCaixa(CaixaEntity caixa, Venda venda) {
		CaixaEntity caixaEntity = repository.getOne(caixa.getCodigo());		
		
		caixaEntity.setTotalDesconto(caixaEntity.getTotalDesconto() + venda.getDesconto() );
		Double totalVendas = caixaEntity.getTotalVendas() + venda.getValorPago();
		caixaEntity.setTotalVendas(totalVendas);
		

		if(caixa.getValorInicial() == null) {
			caixaEntity.setTotal(totalVendas + (double) 0);
		}else {
			caixaEntity.setTotal(totalVendas + caixa.getValorInicial());
		}
		
		return JpaFunctions.caixaEntityToCaixa.apply(repository.saveAndFlush(caixaEntity));
	}
		
	@Override
	@Transactional
	public Long gerarCodigoCaixa() {
		Long codigo = repository.gerarCodigoCaixa();
		if(codigo != null) {
			codigo = codigo +1;
		}else {
			codigo = 0L;
		}			  
		return codigo;  
	}

}
