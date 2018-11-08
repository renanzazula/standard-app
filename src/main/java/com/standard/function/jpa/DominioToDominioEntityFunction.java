package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.DominioEntity;
import com.standard.domain.Dominio;

public class DominioToDominioEntityFunction  implements Function<DominioEntity, Dominio> {

	@Override
	public Dominio apply(DominioEntity input) {
		Dominio output = new Dominio();
		if(input != null){
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
		}
		return output;
	}
}
