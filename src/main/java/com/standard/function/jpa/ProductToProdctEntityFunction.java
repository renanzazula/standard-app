package com.standard.function.jpa;

import com.standard.domain.Product;
import com.standard.entity.ProductEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import com.standard.function.JpaFunctions;

import java.util.Comparator;
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
            output.setCreationDateTime(input.getCreationDateTime());

            if (input.getProvider() != null) {
                output.setProvider(JpaFunctions.providerToProviderEntity.apply(input.getProvider()));
            }

            if (input.getCategory() != null) {
                output.setCategory(JpaFunctions.categoryToCategoryEntity.apply(input.getCategory()));
            }

            if (input.getSubcategory() != null) {
                output.setSubcategory(JpaFunctions.subcategoryToSubCategoryEntity.apply(input.getSubcategory()));
            }

            if (input.getMeasure() != null) {
                output.setMeasure(JpaFunctions.measureToMeasureEntity.apply(input.getMeasure()));
            }

            if (input.getBrand() != null) {
                output.setBrand(JpaFunctions.brandToBrandEntity.apply(input.getBrand()));
            }

            if (input.getProductHasItemsTypeMeasure() != null) {
                output.setProductHasItemsTypeMeasure(input.getProductHasItemsTypeMeasure()
                        .stream()
                        .sorted(Comparator.comparing(ProductHasItemsTypeMeasureEntity::getId))
                        .map(JpaFunctions.productHasItemsTypeMeasureToProductHasItemsTypeMeasureEntity).toList());
            }
        }
        return output;
    }
}
