package com.standard.function.jpa;

import com.standard.domain.OrderHasItemProduct;
import com.standard.entity.OrderHasItemProductEntity;
import com.standard.function.JpaFunctions;

import java.util.Optional;
import java.util.function.Function;

public class OrderHasItemProductToOrderHasItemProductEntityFunction
        implements Function<OrderHasItemProductEntity, OrderHasItemProduct> {

    @Override
    public OrderHasItemProduct apply(OrderHasItemProductEntity input) {
        OrderHasItemProduct output = new OrderHasItemProduct();
        Optional.ofNullable(input)
                .map(OrderHasItemProductEntity::getProductHasItemsTypeMeasure)
                .ifPresent(productHasItemsTypeMeasure -> {
                    output.setProductHasItemsTypeMeasure(
                            JpaFunctions.productHasItemsTypeMeasureToProductHasItemsTypeMeasureEntity.apply(productHasItemsTypeMeasure)
                    );
                    output.setValorUnitario(input.getUnitValue());
                    output.setQuantidade(input.getQuantity());
                });

        return output;
    }

}
