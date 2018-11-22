package com.standard.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "retirada")
public @Data class RetiradaEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@NotNull
	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@Column(name = "valor")
	private Double valor;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "caixa_codigo")
	private CaixaEntity caixa;



}
