package com.standard.service.formaDePagamento;

import com.standard.domain.FormasDePagamento;
import com.standard.entity.FormaDePagamentoEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.FormaDePagamentoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormaDePagamentoServiceImpl implements FormaDePagamentoService {

    private final FormaDePagamentoRepository formaDePagamentoRepository;

    public FormaDePagamentoServiceImpl(FormaDePagamentoRepository formaDePagamentoRepository) {
        this.formaDePagamentoRepository = formaDePagamentoRepository;
    }

    @Override
    @Transactional
    public FormasDePagamento incluir(FormasDePagamento objct) {
        FormaDePagamentoEntity formasDePagamentoDB = new FormaDePagamentoEntity();
        return getFormasDePagamento(objct, formasDePagamentoDB);
    }

    @Override
    @Transactional
    public FormasDePagamento alterar(Long codigo, FormasDePagamento objct) {
        FormaDePagamentoEntity formasDePagamentoDB = formaDePagamentoRepository.getOne(codigo);
        return getFormasDePagamento(objct, formasDePagamentoDB);
    }

    private FormasDePagamento getFormasDePagamento(FormasDePagamento objct, FormaDePagamentoEntity formasDePagamentoDB) {
        formasDePagamentoDB.setNome(objct.getNome());
        formasDePagamentoDB.setDescricao(objct.getDescricao());
        formasDePagamentoDB.setPorcentagemDesconto(objct.getPorcentagemDesconto());
        formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB);
        return JpaFunctions.formasDePagamentoToFormaDePagamentoEntity.apply(formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB));
    }

    @Override
    @Transactional
    public void excluir(Long codigo) {
        FormaDePagamentoEntity formasDePagamentoDB = formaDePagamentoRepository.getOne(codigo);
        if(formasDePagamentoDB != null) {
            formasDePagamentoDB.setStatus(StatusEnum.INATIVO);
        }
        formaDePagamentoRepository.save(formasDePagamentoDB);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "formasDePagamentoListCache", condition = "#showInventoryOnHand == false")
    public List<FormasDePagamento> consultar() {
        return formaDePagamentoRepository.findAll().stream().map(JpaFunctions.formasDePagamentoToFormaDePagamentoEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "formasDePagamentoCache", key = "#codigo", condition = "#showInventoryOnHand == false")
    public FormasDePagamento consultarByCodigo(Long codigo) {
        return JpaFunctions.formasDePagamentoToFormaDePagamentoEntity
                .apply(formaDePagamentoRepository.findById(codigo).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
    }

}
