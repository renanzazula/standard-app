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
			output.setNome(input.getName());
			output.setDescription(input.getDescription());
			output.setStatus(input.getStatus() != null ? input.getStatus().name() : "");
			if (input.getItemsTypeMeasure() != null) {
				output.setItemsTypeMeasure(input.getItemsTypeMeasure().stream().map(JpaFunctions.itemsTypeMeasureToItemsTypeMeasureEntity).collect(Collectors.toList()));
			}
		}
		return output;
	}
}
