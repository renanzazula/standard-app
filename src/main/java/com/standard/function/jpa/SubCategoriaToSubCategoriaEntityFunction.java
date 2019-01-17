package com.standard.function.jpa;

import com.standard.domain.Subcategoria;
import com.standard.entity.SubcategoriaEntity;

import java.util.function.Function;

public class SubCategoriaToSubCategoriaEntityFunction implements Function<SubcategoriaEntity, Subcategoria> {

	@Override
	public Subcategoria apply(SubcategoriaEntity input) {
		Subcategoria output = new Subcategoria();
		if (input != null) {
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
		}
		return output;
	}

}
