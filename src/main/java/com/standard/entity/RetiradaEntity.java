package com.standard.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "retirada")
public @Data class RetiradaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "dataHora")
	private Date dataHora;

	@Column(name = "valor")
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "caixa_codigo")
	private CaixaEntity caixa;



}
