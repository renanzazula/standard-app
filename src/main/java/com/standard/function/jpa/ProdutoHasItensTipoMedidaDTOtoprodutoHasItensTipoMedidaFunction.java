package com.standard.function.jpa;

import com.standard.domain.Product;
import com.standard.domain.ProductHasItemsTypeMeasure;
import com.standard.entity.ProductEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import com.standard.function.JpaFunctions;

import java.util.Optional;
import java.util.function.Function;

class ProdutoHasItensTipoMedidaDTOtoprodutoHasItensTipoMedidaFunction
        implements Function<ProductHasItemsTypeMeasureEntity, ProductHasItemsTypeMeasure> {

    @Override
    public ProductHasItemsTypeMeasure apply(ProductHasItemsTypeMeasureEntity input) {
        ProductHasItemsTypeMeasure output = new ProductHasItemsTypeMeasure();
        if (input != null) {
            output.setId(input.getId());
            Optional.ofNullable(input.getDomains())
                    .ifPresent(domains -> output.setDomains(
                            domains.stream()
                                    .map(JpaFunctions.domainToDomainEntity)
                                    .toList()
                    ));

            Optional.ofNullable(input.getItemsTypeMeasure())
                    .ifPresent(itemsTypeMeasure -> output.setItemsTypeMeasure(
                            JpaFunctions.itemsTypeMeasureToItemsTypeMeasureEntity.apply(itemsTypeMeasure)
                    ));
            output.setProduct(productEntityToProduct(input.getProduct()));
            output.setQuantity(input.getQuantity());
            output.setUnitValue(input.getUnitValue());
        }
        return output;
    }

    private Product productEntityToProduct(ProductEntity input) {
        Product output = new Product();
        output.setId(input.getId());
        output.setBarCode(input.getBarCode());
        output.setName(input.getName());
        output.setStatus(input.getStatus().name());
        output.setDescription(input.getDescription());
        output.setPrice(input.getPrice());
        output.setSalePrice(input.getSalePrice());
        output.setCostPrice(input.getCostPrice());
        output.setDiscountPrice(input.getDiscountPrice());
        output.setDiscount(input.getDiscount());
        output.setWeight(input.getWeight());
        output.setPercent(input.getPercent());
        output.setDiscountPercent(input.getDiscountPercent());

        Optional.ofNullable(input.getProvider())
                .ifPresent(provider -> output.setProvider(JpaFunctions.providerToProviderEntity.apply(provider)));

        Optional.ofNullable(input.getCategory())
                .ifPresent(category -> output.setCategory(JpaFunctions.categoryToCategoryEntity.apply(category)));

        Optional.ofNullable(input.getSubcategory())
                .ifPresent(subcategory -> output.setSubcategory(JpaFunctions.subcategoryToSubCategoryEntity.apply(subcategory)));

        Optional.ofNullable(input.getMeasure())
                .ifPresent(measure -> output.setMeasure(JpaFunctions.measureToMeasureEntity.apply(measure)));

        Optional.ofNullable(input.getBrand())
                .ifPresent(brand -> output.setBrand(JpaFunctions.brandToBrandEntity.apply(brand)));

        return output;
    }

}
