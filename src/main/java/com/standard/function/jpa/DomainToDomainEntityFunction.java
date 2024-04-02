package com.standard.function.jpa;

import com.standard.domain.Domain;
import com.standard.entity.DomainEntity;

import java.util.function.Function;

public class DomainToDomainEntityFunction implements Function<DomainEntity, Domain> {

	@Override
	public Domain apply(DomainEntity input) {
		Domain output = new Domain();
		if(input != null){
			output.setCodigo(input.getId());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
		}
		return output;
	}
}
