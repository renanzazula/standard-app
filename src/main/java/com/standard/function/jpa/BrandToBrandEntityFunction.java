package com.standard.function.jpa;

import com.standard.domain.Brand;
import com.standard.entity.BrandEntity;

import java.util.function.Function;

public class BrandToBrandEntityFunction implements Function<BrandEntity, Brand> {

	@Override
	public Brand apply(BrandEntity input) {
		Brand output = new Brand();
		if (input != null) {
			output.setId(input.getId());
			output.setName(input.getName());
			output.setDescription(input.getDescription());
			output.setStatus(input.getStatus());
		}
		return output;
	}

}
