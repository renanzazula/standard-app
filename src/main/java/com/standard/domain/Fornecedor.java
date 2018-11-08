package com.standard.domain;

import lombok.Data;

import java.io.Serializable;

public @Data class Fornecedor implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String nome;
	private String descricao;

}
