package com.standard.domain;

import com.standard.enums.FlagSiteEnum;
import com.standard.enums.StatusEnum;
import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public   class Produto implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;
	private Long codigo;
	private String barCode;
	private String nome;
	private StatusEnum status;
	private String descricao;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double preco;
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double precoVenda;
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double precoCusto;
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double precoOferta;
	
	private Double desconto;
	private Double peso;
	
	@NumberFormat(style=Style.PERCENT)
	private Integer porcentagem;
	
	@NumberFormat(style=Style.PERCENT)
	private Integer porcentagemDesconto;
	
	// FIXME: um data e uma hora 
	private Date dataHoraCadastro;

	private Fornecedor fornecedor;
	private Medida medida;
	private Categoria categoria;
	private SubCategoria subCategoria;
	private Marca marca;

	private List<Dominio> dominios;
	private List<Fornecedor> fornecedores;
	private List<Categoria> categorias;
	private List<SubCategoria> subCategorias;
	private List<Marca> marcas;
	private List<Medida> medidas;
	private List<ProdutoHasItensTipoMedida> produtoHasItensTipoMedida;
 
	public Integer getQuantidadeTotalEstoque() {
		Integer quantidadeTotalEstoque = 0;
		if(this.produtoHasItensTipoMedida != null) { 
			for (ProdutoHasItensTipoMedida produtoHasItensTipoMedida : this.produtoHasItensTipoMedida) {
				if(produtoHasItensTipoMedida.getQuantidade() != null) { 
					quantidadeTotalEstoque = quantidadeTotalEstoque + produtoHasItensTipoMedida.getQuantidade();
				}
			}
		}
		return quantidadeTotalEstoque;
	}

	public List<FlagSiteEnum> getFlagSite() {
		return Arrays.asList(FlagSiteEnum.values()); 
	}
	
	
}
