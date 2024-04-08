package com.standard.function.jpa;

import com.standard.domain.Venda;
import com.standard.entity.OrderEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;
import java.util.stream.Collectors;

public class VendaToVendaEntityFunction implements Function<OrderEntity, Venda> {

    @Override
    public Venda apply(OrderEntity input) {
        Venda output = new Venda();
        if (input != null) {
            output.setCodigo(input.getId());
            output.setData(input.getCreationDate());
            output.setHora(input.getCreationTime());
            output.setValorTotal(input.getTotalAmount());
            output.setStatus(input.getStatus());
            output.setQuantidade(input.getQuantity());
            output.setSubTotal(input.getSubTotal());
            output.setValorPendente(input.getPendingAmount());
            output.setValorPago(input.getPaidAmount());
            output.setDesconto(input.getDiscount());
            output.setTotalApagar(input.getTotalAmountToPaid());
            output.setTroco(input.getChange());
            output.setPagamento(input.getPagamento());
            output.setValorTotal(input.getTotalAmount());

            if (input.getPaymentMethod() != null) {
                output.setFormaDePagamento(JpaFunctions.paymentMethodToPaymentMethodEntity.apply(input.getPaymentMethod()));
            }
            if (input.getCustomer() != null) {
                output.setCustomer(JpaFunctions.customerToCustomerEntity.apply(input.getCustomer()));
            }

            if (input.getPos() != null) {
                output.setPos(JpaFunctions.posToPosEntity.apply(input.getPos()));
            }

            if (input.getOrderHasItemProduct() != null) {
                output.setVendaHasItemProduto(input.getOrderHasItemProduct().stream().map(JpaFunctions.vendaHasItemProdutoToVendaHasItemEntity).collect(Collectors.toList()));
            }

        }
        return output;
    }

}
