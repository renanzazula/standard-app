package com.standard.function.jpa;

import com.standard.domain.Recebimento;
import com.standard.entity.RecebimentoEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;

public class RecebimentoEntityToRecebimentoFunction implements Function<RecebimentoEntity, Recebimento> {

    @Override
    public Recebimento apply(RecebimentoEntity input) {
        Recebimento output = new Recebimento();
        if (input != null) {
            output.setCodigo(input.getId());
            output.setNome(input.getName());
            output.setDescricao(input.getDescription());
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
