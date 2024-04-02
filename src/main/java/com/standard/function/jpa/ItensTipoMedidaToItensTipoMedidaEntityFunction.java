package com.standard.function.jpa;

import com.standard.domain.ItensTipoMedida;
import com.standard.entity.ItensTipoMedidaEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;

public class ItensTipoMedidaToItensTipoMedidaEntityFunction implements Function<ItensTipoMedidaEntity, ItensTipoMedida> {

	@Override
	public ItensTipoMedida apply(ItensTipoMedidaEntity input) {
		ItensTipoMedida output = new ItensTipoMedida();
		if(input != null) {
			output.setCodigo(input.getId());
			output.setValor(input.getValor());

			if (input.getCategoria() != null) {
				output.setCategory(JpaFunctions.categoryToCategoryEntity.apply(input.getCategoria()));
			}
			if (input.getSubcategoria() != null) {
				output.setSubcategory(JpaFunctions.subcategoryToSubCategoryEntity.apply(input.getSubcategoria()));
			}
			if (input.getMarca() != null) {
				output.setMarca(JpaFunctions.marcaToMarcaEntity.apply(input.getMarca()));
			}

		}
		return output;
	}

}
