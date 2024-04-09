package com.standard.function.jpa;

import com.standard.domain.Recebimento;
import com.standard.entity.PayBackEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;

public class PayBackEntityToPayBackFunction implements Function<PayBackEntity, Recebimento> {

    @Override
    public Recebimento apply(PayBackEntity input) {
        Recebimento output = new Recebimento();
        if (input != null) {
            output.setId(input.getId());
            output.setName(input.getName());
            output.setDescription(input.getDescription());
            output.setValor(input.getAmount());
            if(input.getPos() != null){
                output.setPos(JpaFunctions.posToPosEntity.apply(input.getPos()));
            }
            if(input.getCustomer() != null){
                output.setCustomer(JpaFunctions.customerToCustomerEntity.apply(input.getCustomer()));
            }
        }
        return output;
    }
}
