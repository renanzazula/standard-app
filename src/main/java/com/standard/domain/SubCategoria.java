package com.standard.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public @Data class SubCategoria implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String nome;
	private String descricao;

	// TODO: se eu preciso disso
	private List<Categoria> categoria;

	 

}
