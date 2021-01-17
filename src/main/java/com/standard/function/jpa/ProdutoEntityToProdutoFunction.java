package com.standard.function.jpa;

import com.standard.domain.Produto;
import com.standard.entity.ProdutoEntity;
import com.standard.entity.ProdutoHasItensTipoMedidaEntity;
import com.standard.function.JpaFunctions;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProdutoEntityToProdutoFunction implements Function<ProdutoEntity, Produto> {

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
                output.setFornecedor(JpaFunctions.fornecedorEntityToFornecedor.apply(input.getFornecedor()));
            }

            if (input.getCategoria() != null) {
                output.setCategoria(JpaFunctions.categoriaEntityToCategoria.apply(input.getCategoria()));
            }

            if (input.getSubcategoria() != null) {
                output.setSubcategoria(JpaFunctions.subCategoriaEntityToSubcategoria.apply(input.getSubcategoria()));
            }

            if (input.getMedida() != null) {
                output.setMedida(JpaFunctions.medidaEntityToMedida.apply(input.getMedida()));
            }

            if (input.getMarca() != null) {
                output.setMarca(JpaFunctions.marcaEntityToMarca.apply(input.getMarca()));
            }

            if (input.getProdutoHasItensTipoMedida() != null) {
                output.setProdutoHasItensTipoMedida(input.getProdutoHasItensTipoMedida()
                        .stream()
                        .sorted(Comparator.comparing(ProdutoHasItensTipoMedidaEntity::getCodigo))
                        .map(JpaFunctions.produtoHasItensTipoMedidaEntityToProdutoHasItensTipoMedida).collect(Collectors.toList()));
            }
        }
        return output;
    }
}
