package com.standard.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "venda_has_item_produto")
public @Data class VendaHasItemProdutoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2516119080969832005L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "venda_has_item_produto_codigo")
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "venda_codigo")
	private VendaEntity venda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_has_itens_tipo_medida_codigo", updatable = false)
	@OrderBy("codigo")
	private ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida;

	@Column(name = "valor_unitario")
	private Double valorUnitario;

	@Column(name = "quantidade")
	private Integer quantidade;

	 
}
