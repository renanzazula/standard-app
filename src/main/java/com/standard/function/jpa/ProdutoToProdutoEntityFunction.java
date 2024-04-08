package com.standard.function.jpa;

import com.standard.domain.Produto;
import com.standard.entity.ProductEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import com.standard.function.JpaFunctions;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProdutoToProdutoEntityFunction implements Function<ProductEntity, Produto> {

    @Override
    public Produto apply(ProductEntity input) {
        Produto output = new Produto();
        if (input != null) {
            output.setCodigo(input.getId());
            output.setBarCode(input.getBarCode());
            output.setNome(input.getName());
            output.setStatus(input.getStatus());
            output.setDescricao(input.getDescription());
            output.setPreco(input.getPrice());
            output.setPrecoVenda(input.getSalePrice());
            output.setPrecoCusto(input.getCostPrice());
            output.setPrecoOferta(input.getDiscountPrice());
            output.setDesconto(input.getDiscount());
            output.setPeso(input.getWeight());
            output.setPorcentagem(input.getPercent());
            output.setPorcentagemDesconto(input.getDiscountPercent());
            output.setDataHoraCadastro(input.getCreationDateTime());

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
                output.setProdutoHasItensTipoMedida(input.getProductHasItemsTypeMeasure()
                        .stream()
                        .sorted(Comparator.comparing(ProductHasItemsTypeMeasureEntity::getId))
                        .map(JpaFunctions.produtoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntity).collect(Collectors.toList()));
            }
        }
        return output;
    }
}
