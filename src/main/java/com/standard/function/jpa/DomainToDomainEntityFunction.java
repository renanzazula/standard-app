package com.standard.function.jpa;

import com.standard.domain.Domain;
import com.standard.entity.DomainEntity;

import java.util.function.Function;

public class DomainToDomainEntityFunction implements Function<DomainEntity, Domain> {

	@Override
	public Domain apply(DomainEntity input) {
		Domain output = new Domain();
		if(input != null){
			output.setId(input.getId());
			output.setName(input.getName());
			output.setDescription(input.getDescription());
			output.setStatus(input.getStatus() != null ? input.getStatus().name() : "");
			output.setChecked(false);
		}
		return output;
	}
}
