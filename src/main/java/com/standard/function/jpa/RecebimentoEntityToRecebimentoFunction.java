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
            output.setCodigo(input.getCodigo());
            output.setNome(input.getNome());
            output.setDescricao(input.getDescricao());
            output.setValor(input.getValor());
            if(input.getCaixa() != null){
                output.setCaixa(JpaFunctions.caixaEntityToCaixa.apply(input.getCaixa()));
            }
            if(input.getCliente() != null){
                output.setCliente(JpaFunctions.clienteEntityToCliente.apply(input.getCliente()));
            }
        }
        return output;
    }
}
