package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.OrderHasItemProductEntity;
import com.standard.function.JpaFunctions;
import com.standard.domain.VendaHasItemProduto;

public class VendaHasItemProdutoToVendaHasItemProdutoEntityFunction
		implements Function<OrderHasItemProductEntity, VendaHasItemProduto> {

	@Override
	public VendaHasItemProduto apply(OrderHasItemProductEntity input) {
		VendaHasItemProduto output = new VendaHasItemProduto();
		if (input != null && input.getProductHasItemsTypeMeasure() != null) {
			output.setProdutoHasItensTipoMedida(JpaFunctions.produtoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntity.apply(input.getProductHasItemsTypeMeasure()));
			output.setValorUnitario(input.getUnitValue());
			output.setQuantidade(input.getQuantity());
		}
		return output;
	}

}
