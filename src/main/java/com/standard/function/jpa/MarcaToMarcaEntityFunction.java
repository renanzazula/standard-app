package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.MarcaEntity;
import com.standard.domain.Marca;

public class MarcaToMarcaEntityFunction implements Function<MarcaEntity, Marca> {

	@Override
	public Marca apply(MarcaEntity input) {
		Marca output = new Marca();
		if (input != null) {
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
		}
		return output;
	}

}
