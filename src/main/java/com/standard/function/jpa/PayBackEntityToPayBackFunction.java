package com.standard.function.jpa;

import com.standard.domain.PayBack;
import com.standard.entity.PayBackEntity;
import com.standard.function.JpaFunctions;

import java.util.Optional;
import java.util.function.Function;

public class PayBackEntityToPayBackFunction implements Function<PayBackEntity, PayBack> {

    @Override
    public PayBack apply(PayBackEntity input) {
        PayBack output = new PayBack();
        if (input != null) {
            output.setId(input.getId());
            output.setName(input.getName());
            output.setDescription(input.getDescription());
            output.setValor(input.getAmount());
            Optional.ofNullable(input.getPos())
                    .ifPresent(pos -> output.setPos(JpaFunctions.posToPosEntity.apply(pos)));
            Optional.ofNullable(input.getCustomer())
                    .ifPresent(customer -> output.setCustomer(JpaFunctions.customerToCustomerEntity.apply(customer)));
        }
        return output;
    }
}
