package com.standard.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "recebimento")
public @Data class RecebimentoEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "dataHora")
	private Date dataHora;

	@Column(name = "valor")
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "caixa_codigo")
	private CaixaEntity caixa;

	@ManyToOne
	@JoinColumn(name = "cliente_codigo")
	private ClienteEntity cliente;

	 

}
