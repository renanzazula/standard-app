package com.standard.function.jpa;

import com.standard.domain.Subcategoria;
import com.standard.entity.SubCategoriaEntity;

import java.util.function.Function;

public class SubCategoriaToSubCategoriaEntityFunction implements Function<SubCategoriaEntity, Subcategoria> {

	@Override
	public Subcategoria apply(SubCategoriaEntity input) {
		Subcategoria output = new Subcategoria();
		if (input != null) {
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
		}
		return output;
	}

}
