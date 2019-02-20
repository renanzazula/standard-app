package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@NotNull
	@Min(0)
	@Max(100)
	@Column(name = "porcentagemDesconto")
	private int porcentagemDesconto;

}
