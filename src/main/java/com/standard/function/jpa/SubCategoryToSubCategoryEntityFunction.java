package com.standard.function.jpa;

import com.standard.domain.Subcategory;
import com.standard.entity.SubcategoryEntity;

import java.util.function.Function;

public class SubCategoryToSubCategoryEntityFunction implements Function<SubcategoryEntity, Subcategory> {

	@Override
	public Subcategory apply(SubcategoryEntity input) {
		Subcategory output = new Subcategory();
		if (input != null) {
			output.setId(input.getId());
			output.setName(input.getName());
			output.setDescription(input.getDescription());
			output.setStatus(input.getStatus() != null ? input.getStatus().name() : "");
		}
		return output;
	}

}
