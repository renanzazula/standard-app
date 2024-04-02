package com.standard.function.jpa;

import com.standard.domain.Produto;
import com.standard.domain.ProdutoHasItensTipoMedida;
import com.standard.entity.ProdutoEntity;
import com.standard.entity.ProdutoHasItensTipoMedidaEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;
import java.util.stream.Collectors;

class ProdutoHasItensTipoMedidaDTOtoprodutoHasItensTipoMedidaFunction
        implements Function<ProdutoHasItensTipoMedidaEntity, ProdutoHasItensTipoMedida> {

    @Override
    public ProdutoHasItensTipoMedida apply(ProdutoHasItensTipoMedidaEntity input) {
        ProdutoHasItensTipoMedida output = new ProdutoHasItensTipoMedida();
        if (input != null) {
            output.setCodigo(input.getId());
            if (input.getDomains() != null) {
                output.setDomains(input.getDomains().stream().map(JpaFunctions.domainToDomainEntity).collect(Collectors.toList()));
            }
            if (input.getItensTipoMedida() != null) {
                output.setItemsTypeMeasure(JpaFunctions.itensTipoMedidaToItensTipoMedidaEntity.apply(input.getItensTipoMedida()));
            }

            output.setProduto(produtoEntityToProduto(input.getProduto()));
            output.setQuantidade(input.getQuantidade());
            output.setValorUnitario(input.getValorUnitario());
        }
        return output;
    }

    /**
     * @param input
     * @return
     */
    private Produto produtoEntityToProduto(ProdutoEntity input) {
        Produto output = new Produto();
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
        return output;
    }

}
