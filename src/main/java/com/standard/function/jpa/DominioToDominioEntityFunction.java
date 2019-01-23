package com.standard.function.jpa;

import com.standard.domain.Dominio;
import com.standard.entity.DominioEntity;

import java.util.function.Function;

public class DominioToDominioEntityFunction  implements Function<DominioEntity, Dominio> {

	@Override
	public Dominio apply(DominioEntity input) {
		Dominio output = new Dominio();
		if(input != null){
			output.setCodigo(input.getCodigo());
			output.setNome(input.getNome());
			output.setDescricao(input.getDescricao());
//			output.setData(input.getDataCriacao());
//			output.setHora(input.getHoraCriacao());
		}
		return output;
	}
}
