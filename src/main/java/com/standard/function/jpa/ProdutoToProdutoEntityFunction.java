package com.standard.function.jpa;

import com.standard.domain.Produto;
import com.standard.entity.ProdutoEntity;
import com.standard.function.JpaFunctions;

import java.util.function.Function;
import java.util.stream.Collectors;

public class ProdutoToProdutoEntityFunction implements Function<ProdutoEntity, Produto> {

    @Override
    public Produto apply(ProdutoEntity input) {
        Produto output = new Produto();
        if (input != null) {
            output.setCodigo(input.getCodigo());
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

            if (input.getFornecedor() != null) {
                output.setFornecedor(JpaFunctions.fornecedortoFornecedorEntity.apply(input.getFornecedor()));
            }

            if (input.getCategoria() != null) {
                output.setCategoria(JpaFunctions.categoriaToCategoriaEntity.apply(input.getCategoria()));
            }

            if (input.getSubCategoria() != null) {
                output.setSubCategoria(JpaFunctions.subCategoriaToSubCategoriaEntity.apply(input.getSubCategoria()));
            }

            if (input.getMedida() != null) {
                output.setMedida(JpaFunctions.medidaToMedidaEntity.apply(input.getMedida()));
            }

            if (input.getMarca() != null) {
                output.setMarca(JpaFunctions.marcaToMarcaEntity.apply(input.getMarca()));
            }

            if (input.getProdutoHasItensTipoMedida() != null) {
                output.setProdutoHasItensTipoMedida(input.getProdutoHasItensTipoMedida().stream().map(JpaFunctions.produtoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntity).collect(Collectors.toList()));
            }
        }
        return output;
    }
}
