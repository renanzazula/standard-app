package com.standard.service.FormaDePagamento;

import com.standard.domain.FormasDePagamento;
import com.standard.entity.FormaDePagamentoEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.FormaDePagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormaDePagamentoServiceImpl implements FormaDePagamentoService {

    private FormaDePagamentoRepository formaDePagamentoRepository;

    public FormaDePagamentoServiceImpl(FormaDePagamentoRepository formaDePagamentoRepository) {
        this.formaDePagamentoRepository = formaDePagamentoRepository;
    }

    @Override
    public FormasDePagamento incluir(FormasDePagamento objct) {
        FormaDePagamentoEntity formasDePagamentoDB = new FormaDePagamentoEntity();
        formasDePagamentoDB.setNome(objct.getNome());
        formasDePagamentoDB.setDescricao(objct.getDescricao());
        formasDePagamentoDB.setPorcentagemDesconto(objct.getPorcentagemDesconto());
        formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB);
        return JpaFunctions.formasDePagamentoToFormaDePagamentoEntity
                .apply(formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB));
    }

    @Override
    public FormasDePagamento alterar(Integer codigo, FormasDePagamento objct) {
        FormaDePagamentoEntity formasDePagamentoDB = formaDePagamentoRepository.getOne(codigo);
        formasDePagamentoDB.setNome(objct.getNome());
        formasDePagamentoDB.setDescricao(objct.getDescricao());
        formasDePagamentoDB.setPorcentagemDesconto(objct.getPorcentagemDesconto());
        formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB);
        return JpaFunctions.formasDePagamentoToFormaDePagamentoEntity
                .apply(formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB));
    }

    @Override
    public void excluir(Integer codigo) {
        FormaDePagamentoEntity formasDePagamentoDB = formaDePagamentoRepository.getOne(codigo);
        formaDePagamentoRepository.delete(formasDePagamentoDB);
    }

    @Override
    public List<FormasDePagamento> consultar() {
        return formaDePagamentoRepository.findAll().stream().map(JpaFunctions.formasDePagamentoToFormaDePagamentoEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FormasDePagamento consultarByCodigo(Integer codigo) {
        return JpaFunctions.formasDePagamentoToFormaDePagamentoEntity
                .apply(formaDePagamentoRepository.getOne(codigo));
    }

}
