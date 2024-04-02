package com.standard.function.jpa;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.standard.domain.Measure;
import com.standard.entity.MeasureEntity;
import com.standard.function.JpaFunctions;

public class MeasureToMeasureEntityFunction implements Function<MeasureEntity, Measure> {

	@Override
	public Measure apply(MeasureEntity input) {
		Measure output = new Measure();
		if (input != null) {
			output.setId(input.getId());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());

			if (input.getItensTipoMedida() != null) {
				output.setItemsTypeMeasure(input.getItensTipoMedida().stream().map(JpaFunctions.itensTipoMedidaToItensTipoMedidaEntity).collect(Collectors.toList()));
			}
		}
		return output;
	}
}
