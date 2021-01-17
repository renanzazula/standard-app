package com.standard.function.jpa;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.standard.entity.MedidaEntity;
import com.standard.function.JpaFunctions;
import com.standard.domain.Medida;

public class MedidaEntityToMedidaFunction implements Function<MedidaEntity, Medida> {

	@Override
	public Medida apply(MedidaEntity input) {
		Medida output = new Medida();
		if (input != null) {
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());

			if (input.getItensTipoMedida() != null) {
				output.setItensTipoMedida(input.getItensTipoMedida().stream().map(JpaFunctions.itensTipoMedidaEntityToItensTipoMedida).collect(Collectors.toList()));
			}
		}
		return output;
	}
}
