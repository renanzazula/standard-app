package com.standard.function.jpa;

import com.standard.domain.Produto;
import com.standard.domain.ProdutoHasItensTipoMedida;
import com.standard.entity.DomainEntity;
import com.standard.entity.ProductEntity;
import com.standard.entity.ProductHasItemsTypeMeasureEntity;
import com.standard.function.JpaFunctions;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProdutoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntityFunction
		implements Function<ProductHasItemsTypeMeasureEntity, ProdutoHasItensTipoMedida> {

	@Override
	public ProdutoHasItensTipoMedida apply(ProductHasItemsTypeMeasureEntity input) {
		ProdutoHasItensTipoMedida output = new ProdutoHasItensTipoMedida();
		if (input != null) {
			output.setCodigo(input.getId());
			if (input.getDomains() != null) {
				output.setDomains(input.getDomains()
						.stream()
						.sorted(Comparator.comparing(DomainEntity::getId))
						.map(JpaFunctions.domainToDomainEntity).collect(Collectors.toList()));
			}
			if (input.getItemsTypeMeasure() != null) {
				output.setItemsTypeMeasure(JpaFunctions.itensTipoMedidaToItensTipoMedidaEntity.apply(input.getItemsTypeMeasure()));
			}

			if (input.getProduct() != null) {
				output.setProduto(produtoEntityToProduto(input.getProduct()));
			}

			output.setQuantidade(input.getQuantity());
			output.setValorUnitario(input.getUnitValue());
		}
		return output;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private Produto produtoEntityToProduto(ProductEntity input){
		Produto output = new Produto();
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
		
		if(input.getProvider() != null) {
			output.setProvider(JpaFunctions.providerToProviderEntity.apply(input.getProvider()));
		}

		if(input.getCategory() !=  null) {
			output.setCategory(JpaFunctions.categoryToCategoryEntity.apply(input.getCategory()));
		}
		
		if(input.getSubcategory() != null) {
			output.setSubcategory(JpaFunctions.subcategoryToSubCategoryEntity.apply(input.getSubcategory()));
		}
		
		if(input.getMeasure() != null) {
			output.setMeasure(JpaFunctions.measureToMeasureEntity.apply(input.getMeasure()));
		}
		
		if (input.getBrand() != null) {
			output.setBrand(JpaFunctions.brandToBrandEntity.apply(input.getBrand()));
		}
		return output;
	} 

}
