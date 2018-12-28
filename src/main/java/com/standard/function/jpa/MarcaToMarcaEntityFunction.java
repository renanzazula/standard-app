package com.standard.function.jpa;

import com.standard.domain.Marca;
import com.standard.entity.MarcaEntity;

import java.util.function.Function;

public class MarcaToMarcaEntityFunction implements Function<MarcaEntity, Marca> {

	@Override
	public Marca apply(MarcaEntity input) {
		Marca output = new Marca();
		if (input != null) {
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
			output.setData(input.getDataCriacao());
			output.setHora(input.getHoraCriacao());
			output.setDataAlteracao(input.getDataAlteracao());
			output.setHoraAlteracao(input.getDataAlteracao());
			output.setStatus(input.getStatus());
		}
		return output;
	}

}
