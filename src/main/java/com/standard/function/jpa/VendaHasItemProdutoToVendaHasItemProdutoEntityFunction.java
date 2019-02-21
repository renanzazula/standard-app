package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.VendaHasItemProdutoEntity;
import com.standard.function.JpaFunctions;
import com.standard.domain.VendaHasItemProduto;

public class VendaHasItemProdutoToVendaHasItemProdutoEntityFunction
		implements Function<VendaHasItemProdutoEntity, VendaHasItemProduto> {

	@Override
	public VendaHasItemProduto apply(VendaHasItemProdutoEntity input) {
		VendaHasItemProduto output = new VendaHasItemProduto();
		if (input != null && input.getProdutoHasItensTipoMedida() != null) {
			output.setProdutoHasItensTipoMedida(JpaFunctions.produtoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntity.apply(input.getProdutoHasItensTipoMedida()));
			output.setValorUnitario(input.getValorUnitario());
			output.setQuantidade(input.getQuantidade());
		}
		return output;
	}

}
