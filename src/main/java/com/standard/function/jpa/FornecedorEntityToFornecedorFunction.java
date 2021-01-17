package com.standard.function.jpa;

import com.standard.domain.Fornecedor;
import com.standard.entity.FornecedorEntity;

import java.util.function.Function;

public class FornecedorEntityToFornecedorFunction implements Function<FornecedorEntity, Fornecedor> {

    @Override
    public Fornecedor apply(FornecedorEntity input) {
        Fornecedor output = new Fornecedor();
        if (input != null) {
            output.setCodigo(input.getCodigo());
            output.setNome(input.getNome());
            output.setDescricao(input.getDescricao());
        }
        return output;
    }

}
