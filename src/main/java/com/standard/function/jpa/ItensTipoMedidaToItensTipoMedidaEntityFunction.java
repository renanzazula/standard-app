package com.standard.function.jpa;

import com.standard.domain.ItemsTypeMeasure;
import com.standard.entity.ItemsTypeMeasureEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;

public class ItensTipoMedidaToItensTipoMedidaEntityFunction implements Function<ItemsTypeMeasureEntity, ItemsTypeMeasure> {

	@Override
	public ItemsTypeMeasure apply(ItemsTypeMeasureEntity input) {
		ItemsTypeMeasure output = new ItemsTypeMeasure();
		if(input != null) {
			output.setId(input.getId());
			output.setValor(input.getValor());

			if (input.getCategory() != null) {
				output.setCategory(JpaFunctions.categoryToCategoryEntity.apply(input.getCategory()));
			}
			if (input.getSubcategory() != null) {
				output.setSubcategory(JpaFunctions.subcategoryToSubCategoryEntity.apply(input.getSubcategory()));
			}
			if (input.getBrand() != null) {
				output.setBrand(JpaFunctions.brandToBrandEntity.apply(input.getBrand()));
			}

		}
		return output;
	}

}
