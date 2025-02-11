package com.standard.function.jpa;

import com.standard.domain.Measure;
import com.standard.entity.MeasureEntity;
import com.standard.function.JpaFunctions;

import java.util.Optional;
import java.util.function.Function;

public class MeasureToMeasureEntityFunction implements Function<MeasureEntity, Measure> {

	@Override
	public Measure apply(MeasureEntity input) {
		Measure output = new Measure();
		if (input != null) {
			output.setId(input.getId());
			output.setNome(input.getName());
			output.setDescription(input.getDescription());
			output.setStatus(input.getStatus() != null ? input.getStatus().name() : "");

			Optional.ofNullable(input.getItemsTypeMeasure())
					.ifPresent(itemsTypeMeasure -> output.setItemsTypeMeasure(
							itemsTypeMeasure.stream()
									.map(JpaFunctions.itemsTypeMeasureToItemsTypeMeasureEntity)
									.toList()
					));
		}
		return output;
	}
}
