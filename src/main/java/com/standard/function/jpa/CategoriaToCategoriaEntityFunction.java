package com.standard.function.jpa;

import com.standard.domain.Categoria;
import com.standard.entity.CategoriaEntity;
import com.standard.function.JpaFunctions;

import java.util.stream.Collectors;

public class CategoriaToCategoriaEntityFunction implements java.util.function.Function<CategoriaEntity, Categoria> {

    @Override
    public Categoria apply(CategoriaEntity input) {
        Categoria output = new Categoria();
        if (input != null) {
            output.setCodigo(input.getCodigo());
            output.setNome(input.getNome());
            output.setDescricao(input.getDescricao());
            if (input.getSubCategoriasSet() != null) {
                output.setSubcategorias(input.getSubCategoriasSet().stream().map(JpaFunctions.subCategoriaToSubCategoriaEntity).collect(Collectors.toList()));
            }
        }
        return output;
    }

}
