package com.standard.function.jpa;

import com.standard.domain.Retirada;
import com.standard.entity.RetiradaEntity;
import com.standard.function.JpaFunctions;

public class RetiradaEntityToRetiradaFunction implements java.util.function.Function<RetiradaEntity, Retirada> {

    @Override
    public Retirada apply(RetiradaEntity input) {
        Retirada output = new Retirada();
        if (input != null) {
            output.setCodigo(input.getCodigo());
            output.setDescricao(input.getDescricao());
            output.setValor(input.getValor());
            output.setData(input.getDataCriacao());
            output.setHora(input.getHoraCriacao());
            if(input.getCaixa() != null){
                output.setCaixa(JpaFunctions.caixaToCaixaEntity.apply(input.getCaixa()));
            }
        }
        return output;
    }
}
