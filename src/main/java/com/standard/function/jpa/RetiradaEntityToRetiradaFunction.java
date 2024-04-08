package com.standard.function.jpa;

import com.standard.domain.Retirada;
import com.standard.entity.RetiradaEntity;
import com.standard.function.JpaFunctions;

public class RetiradaEntityToRetiradaFunction implements java.util.function.Function<RetiradaEntity, Retirada> {

    @Override
    public Retirada apply(RetiradaEntity input) {
        Retirada output = new Retirada();
        if (input != null) {
            output.setCodigo(input.getId());
            output.setDescricao(input.getDescription());
            output.setValor(input.getAmount());
            output.setData(input.getCreationDate());
            output.setHora(input.getCreationTime());
            if(input.getCaixa() != null){
                output.setPos(JpaFunctions.posToPosEntity.apply(input.getCaixa()));
            }
        }
        return output;
    }
}
