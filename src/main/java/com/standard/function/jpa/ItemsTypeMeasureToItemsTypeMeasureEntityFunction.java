package com.standard.function.jpa;

import com.standard.domain.ItemsTypeMeasure;
import com.standard.entity.ItemsTypeMeasureEntity;
import com.standard.function.JpaFunctions;

import java.util.Optional;
import java.util.function.Function;

public class ItemsTypeMeasureToItemsTypeMeasureEntityFunction implements Function<ItemsTypeMeasureEntity, ItemsTypeMeasure> {

	@Override
	public ItemsTypeMeasure apply(ItemsTypeMeasureEntity input) {
		ItemsTypeMeasure output = new ItemsTypeMeasure();
		if(input != null) {
			output.setId(input.getId());
			output.setAmount(input.getAmount());
			Optional.ofNullable(input.getCategory())
					.ifPresent(category -> output.setCategory(JpaFunctions.categoryToCategoryEntity.apply(category)));
			Optional.ofNullable(input.getSubcategory())
					.ifPresent(subcategory -> output.setSubcategory(JpaFunctions.subcategoryToSubCategoryEntity.apply(subcategory)));
			Optional.ofNullable(input.getBrand())
					.ifPresent(brand -> output.setBrand(JpaFunctions.brandToBrandEntity.apply(brand)));
		}
		return output;
	}

}
