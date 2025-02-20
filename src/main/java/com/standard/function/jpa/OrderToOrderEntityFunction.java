package com.standard.function.jpa;

import com.standard.domain.Order;
import com.standard.entity.OrderEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;

public class OrderToOrderEntityFunction implements Function<OrderEntity, Order> {

    @Override
    public Order apply(OrderEntity input) {
        Order output = new Order();
        if (input != null) {
            output.setId(input.getId());
            output.setCreationDate(input.getCreationDate());
            output.setCreationTime(input.getCreationTime());
            output.setTotalAmount(input.getTotalAmount());
            output.setStatus(input.getStatus().name());
            output.setQuantity(input.getQuantity());
            output.setSubTotal(input.getSubTotal());
            output.setPendingAmount(input.getPendingAmount());
            output.setPaidAmount(input.getPaidAmount());
            output.setDiscount(input.getDiscount());
            output.setTotalAmountToPaid(input.getTotalAmountToPaid());
            output.setChange(input.getChanging());
            output.setPayment(input.getPayment());

            if (input.getPaymentMethod() != null) {
                output.setPaymentMethod(JpaFunctions.paymentMethodToPaymentMethodEntity.apply(input.getPaymentMethod()));
            }
            if (input.getCustomer() != null) {
                output.setCustomer(JpaFunctions.customerToCustomerEntity.apply(input.getCustomer()));
            }

            if (input.getPos() != null) {
                output.setPos(JpaFunctions.posToPosEntity.apply(input.getPos()));
            }

            if (input.getOrderHasItemProduct() != null) {
                output.setOrderHasItemProduct(input.getOrderHasItemProduct().stream().map(JpaFunctions.orderHasItemProductToOrderHasItemProductEntity).toList());
            }

        }
        return output;
    }

}
