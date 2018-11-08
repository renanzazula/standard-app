package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.SubCategoriaEntity;
import com.standard.domain.SubCategoria;

public class SubCategoriaToSubCategoriaEntityFunction implements Function<SubCategoriaEntity, SubCategoria> {

	@Override
	public SubCategoria apply(SubCategoriaEntity input) {
		SubCategoria output = new SubCategoria();
		if (input != null) {
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
		}
		return output;
	}

}
