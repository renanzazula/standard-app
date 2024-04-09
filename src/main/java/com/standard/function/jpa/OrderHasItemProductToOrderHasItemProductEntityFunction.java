package com.standard.function.jpa;

import java.util.function.Function;

import com.standard.entity.OrderHasItemProductEntity;
import com.standard.function.JpaFunctions;
import com.standard.domain.OrderHasItemProduct;

public class OrderHasItemProductToOrderHasItemProductEntityFunction
		implements Function<OrderHasItemProductEntity, OrderHasItemProduct> {

	@Override
	public OrderHasItemProduct apply(OrderHasItemProductEntity input) {
		OrderHasItemProduct output = new OrderHasItemProduct();
		if (input != null && input.getProductHasItemsTypeMeasure() != null) {
			output.setProductHasItemsTypeMeasure(JpaFunctions.productHasItemsTypeMeasureToProductHasItemsTypeMeasureEntity.apply(input.getProductHasItemsTypeMeasure()));
			output.setValorUnitario(input.getUnitValue());
			output.setQuantidade(input.getQuantity());
		}
		return output;
	}

}
