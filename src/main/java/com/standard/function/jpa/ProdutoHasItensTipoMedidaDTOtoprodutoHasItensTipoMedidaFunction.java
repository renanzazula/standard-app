package com.standard.function.jpa;

import com.standard.domain.Product;
import com.standard.domain.ProductHasItemsTypeMeasure;
import com.standard.entity.ProductEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;
import java.util.stream.Collectors;

class ProdutoHasItensTipoMedidaDTOtoprodutoHasItensTipoMedidaFunction
        implements Function<ProductHasItemsTypeMeasureEntity, ProductHasItemsTypeMeasure> {

    @Override
    public ProductHasItemsTypeMeasure apply(ProductHasItemsTypeMeasureEntity input) {
        ProductHasItemsTypeMeasure output = new ProductHasItemsTypeMeasure();
        if (input != null) {
            output.setId(input.getId());
            if (input.getDomains() != null) {
                output.setDomains(input.getDomains().stream().map(JpaFunctions.domainToDomainEntity).collect(Collectors.toList()));
            }
            if (input.getItemsTypeMeasure() != null) {
                output.setItemsTypeMeasure(JpaFunctions.itemsTypeMeasureToItemsTypeMeasureEntity.apply(input.getItemsTypeMeasure()));
            }

            output.setProduct(produtoEntityToProduto(input.getProduct()));
            output.setQuantity(input.getQuantity());
            output.setUnitValue(input.getUnitValue());
        }
        return output;
    }

    /**
     * @param input
     * @return
     */
    private Product produtoEntityToProduto(ProductEntity input) {
        Product output = new Product();
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
        return output;
    }

}
