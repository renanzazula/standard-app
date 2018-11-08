package com.standard.function.jpa;

import com.standard.domain.Venda;
import com.standard.entity.VendaEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;
import java.util.stream.Collectors;

public class VendaToVendaEntityFunction implements Function<VendaEntity, Venda> {

    @Override
    public Venda apply(VendaEntity input) {
        Venda output = new Venda();
        if (input != null) {
            output.setCodigo(input.getCodigo());
            output.setData(input.getData());
            output.setHora(input.getHora());
            output.setValorTotal(input.getValorTotal());
            output.setStatus(input.getStatus());
            output.setQuantidade(input.getQuantidade());
            output.setSubTotal(input.getSubTotal());
            output.setValorPendente(input.getValorPendente());
            output.setValorPago(input.getValorPago());
            output.setDesconto(input.getDesconto());
            output.setTotalApagar(input.getTotalApagar());
            output.setTroco(input.getTroco());
            output.setPagamento(input.getPagamento());
            output.setValorTotal(input.getValorTotal());

            if (input.getFormaDePagamento() != null) {
                output.setFormaDePagamento(JpaFunctions.formasDePagamentoToFormaDePagamentoEntity.apply(input.getFormaDePagamento()));
            }
            if (input.getCliente() != null) {
                output.setCliente(JpaFunctions.clienteToClienteEntity.apply(input.getCliente()));
            }

            if (input.getCaixa() != null) {
                output.setCaixa(JpaFunctions.caixaToCaixaEntity.apply(input.getCaixa()));
            }

            if (input.getVendaHasItemProduto() != null) {
                output.setVendaHasItemProduto(input.getVendaHasItemProduto().stream().map(JpaFunctions.vendaHasItemProdutoToVendaHasItemEntity).collect(Collectors.toList()));
            }

        }
        return output;
    }

}
