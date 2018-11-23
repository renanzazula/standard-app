package com.standard.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "formaDePagamento")
public @Data class FormaDePagamentoEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "nome", length = 45)
	private String nome;

	@NotNull
	@Column(name = "descricao", length = 45)
	private String descricao;

	@NotNull
	@Min(0)
	@Max(100)
	@Column(name = "porcentagemDesconto")
	private int porcentagemDesconto;

}
