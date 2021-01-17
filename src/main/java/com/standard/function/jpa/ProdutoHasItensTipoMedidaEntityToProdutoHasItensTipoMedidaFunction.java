package com.standard.function.jpa;

import com.standard.domain.Produto;
import com.standard.domain.ProdutoHasItensTipoMedida;
import com.standard.entity.DominioEntity;
import com.standard.entity.ProdutoEntity;
import com.standard.entity.ProdutoHasItensTipoMedidaEntity;
import com.standard.function.JpaFunctions;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProdutoHasItensTipoMedidaEntityToProdutoHasItensTipoMedidaFunction
		implements Function<ProdutoHasItensTipoMedidaEntity, ProdutoHasItensTipoMedida> {

	@Override
	public ProdutoHasItensTipoMedida apply(ProdutoHasItensTipoMedidaEntity input) {
		ProdutoHasItensTipoMedida output = new ProdutoHasItensTipoMedida();
		if (input != null) {
			output.setCodigo(input.getCodigo());
			if (input.getDominios() != null) {
				output.setDominios(input.getDominios()
						.stream()
						.sorted(Comparator.comparing(DominioEntity::getCodigo))
						.map(JpaFunctions.dominioEntityToDominio).collect(Collectors.toList()));
			}
			if (input.getItensTipoMedida() != null) {
				output.setItensTipoMedida(JpaFunctions.itensTipoMedidaEntityToItensTipoMedida.apply(input.getItensTipoMedida()));
			}

			if (input.getProduto() != null) {
				output.setProduto(produtoEntityToProduto(input.getProduto()));
			}

			output.setQuantidade(input.getQuantidade());
			output.setValorUnitario(input.getValorUnitario());
		}
		return output;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private Produto produtoEntityToProduto(ProdutoEntity input){
		Produto output = new Produto();
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
		
		if(input.getFornecedor() != null) {
			output.setFornecedor(JpaFunctions.fornecedorEntityToFornecedor.apply(input.getFornecedor()));
		}

		if(input.getCategoria() !=  null) {
			output.setCategoria(JpaFunctions.categoriaEntityToCategoria.apply(input.getCategoria()));
		}
		
		if(input.getSubcategoria() != null) {
			output.setSubcategoria(JpaFunctions.subCategoriaEntityToSubcategoria.apply(input.getSubcategoria()));
		}
		
		if(input.getMedida() != null) {
			output.setMedida(JpaFunctions.medidaEntityToMedida.apply(input.getMedida()));
		}
		
		if (input.getMarca() != null) {
			output.setMarca(JpaFunctions.marcaEntityToMarca.apply(input.getMarca()));
		}
		return output;
	} 

}
