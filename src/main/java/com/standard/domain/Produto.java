package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.enums.StatusEnum;
import com.standard.util.Constants;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;
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

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double preco;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double precoVenda;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double precoCusto;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double precoOferta;
	
	private Double desconto;
	private Double peso;
	
	@NumberFormat(style=Style.PERCENT)
	private Integer porcentagem;
	
	@NumberFormat(style=Style.PERCENT)
	private Integer porcentagemDesconto;

	@JsonFormat(pattern=Constants.PATTERN_DATE_TIME_FORMAT)
	private Date dataHoraCadastro;

	private Fornecedor fornecedor;
	private Medida medida;
	private Categoria categoria;
	private Subcategoria subcategoria;
	private Marca marca;

	private List<Dominio> dominios;
	private List<Fornecedor> fornecedores;
	private List<Categoria> categorias;
	private List<Subcategoria> subcategorias;
	private List<Marca> marcas;
	private List<Medida> medidas;
	private List<ProdutoHasItensTipoMedida> produtoHasItensTipoMedida;
 	private Integer quantidadeTotalEstoque;
			
	public Integer getQuantidadeTotalEstoque() {
		Integer quantidadeTotalEstoque = 0;
		if(this.produtoHasItensTipoMedida != null) { 
			for (ProdutoHasItensTipoMedida correnteProdutoHasItensTipoMedida : this.produtoHasItensTipoMedida) {
				if(correnteProdutoHasItensTipoMedida.getQuantidade() != null) {
					quantidadeTotalEstoque = quantidadeTotalEstoque + correnteProdutoHasItensTipoMedida.getQuantidade();
				}
			}
		}
		return quantidadeTotalEstoque;
	}
}
