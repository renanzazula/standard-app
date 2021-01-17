package com.standard.function.jpa;

import com.standard.domain.Marca;
import com.standard.entity.MarcaEntity;

import java.util.function.Function;

public class MarcaEntityToMarcaFunction implements Function<MarcaEntity, Marca> {

	@Override
	public Marca apply(MarcaEntity input) {
		Marca output = new Marca();
		if (input != null) {
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
			output.setStatus(input.getStatus());
		}
		return output;
	}

}
