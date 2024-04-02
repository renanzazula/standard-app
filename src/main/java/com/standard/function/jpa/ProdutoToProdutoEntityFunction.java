package com.standard.function.jpa;

import com.standard.domain.Produto;
import com.standard.entity.ProdutoEntity;
import com.standard.entity.ProdutoHasItensTipoMedidaEntity;
import com.standard.function.JpaFunctions;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProdutoToProdutoEntityFunction implements Function<ProdutoEntity, Produto> {

    @Override
    public Produto apply(ProdutoEntity input) {
        Produto output = new Produto();
        if (input != null) {
            output.setCodigo(input.getId());
            output.setBarCode(input.getBarCode());
            output.setNome(input.getNome());
            output.setStatus(input.getStatus());
            output.setDescricao(input.getDescricao());
            output.setPreco(input.getPreco());
            output.setPrecoVenda(input.getPrecoVenda());
            output.setPrecoCusto(input.getPrecoCusto());
            output.setPrecoOferta(input.getPrecoOferta());
            output.setDesconto(input.getDesconto());
            output.setPeso(input.getPeso());
            output.setPorcentagem(input.getPorcentagem());
            output.setPorcentagemDesconto(input.getPorcentagemDesconto());
            output.setDataHoraCadastro(input.getDataHoraCadastro());

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

            if (input.getProdutoHasItensTipoMedida() != null) {
                output.setProdutoHasItensTipoMedida(input.getProdutoHasItensTipoMedida()
                        .stream()
                        .sorted(Comparator.comparing(ProdutoHasItensTipoMedidaEntity::getId))
                        .map(JpaFunctions.produtoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntity).collect(Collectors.toList()));
            }
        }
        return output;
    }
}
