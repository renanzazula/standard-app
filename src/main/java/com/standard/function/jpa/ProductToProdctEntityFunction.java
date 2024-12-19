package com.standard.function.jpa;

import com.standard.domain.Product;
import com.standard.entity.ProductEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import com.standard.function.JpaFunctions;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public class ProductToProdctEntityFunction implements Function<ProductEntity, Product> {

    @Override
    public Product apply(ProductEntity input) {
        Product output = new Product();

        if (input != null) {
            output.setId(input.getId());
            output.setBarCode(input.getBarCode());
            output.setName(input.getName());
            output.setStatus(input.getStatus());
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


            Optional.ofNullable(input.getProductHasItemsTypeMeasure())
                    .ifPresent(measures -> output.setProductHasItemsTypeMeasure(
                            measures.stream()
                                    .sorted(Comparator.comparing(ProductHasItemsTypeMeasureEntity::getId))
                                    .map(JpaFunctions.productHasItemsTypeMeasureToProductHasItemsTypeMeasureEntity)
                                    .toList()
                    ));

        }
        return output;
    }
}
