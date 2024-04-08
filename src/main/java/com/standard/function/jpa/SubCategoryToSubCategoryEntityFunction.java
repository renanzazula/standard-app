package com.standard.function.jpa;

import com.standard.domain.Subcategory;
import com.standard.entity.SubcategoryEntity;

import java.util.function.Function;

public class SubCategoryToSubCategoryEntityFunction implements Function<SubcategoryEntity, Subcategory> {

	@Override
	public Subcategory apply(SubcategoryEntity input) {
		Subcategory output = new Subcategory();
		if (input != null) {
			output.setCodigo(input.getId());
			output.setNome(input.getName());
			output.setDescricao(input.getDescription());
		}
		return output;
	}

}
