package com.standard.domain;

import lombok.Data;

import java.io.Serializable;

public @Data class Dominio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4933949406995695753L;

	private Long codigo;
	private String nome;
	private String descricao;
	private boolean checked;

}
