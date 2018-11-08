package com.standard.domain;

import lombok.Data;

import java.io.Serializable;

public @Data class FormasDePagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String nome;
	private String descricao;
	private Integer porcentagemDesconto;

}
