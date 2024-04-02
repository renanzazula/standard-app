package com.standard.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "venda_has_item_produto")
public @Data class VendaHasItemProdutoEntity extends BaseAuditEntity{

	private static final long serialVersionUID = -2516119080969832005L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "venda_has_item_produto_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "venda_id")
	private VendaEntity venda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_has_itens_tipo_medida_id", updatable = false)
	@OrderBy("id")
	private ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida;

	@Column(name = "valor_unitario")
	private Double valorUnitario;

	@Column(name = "quantidade")
	private Integer quantidade;

	 
}
