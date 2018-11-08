package com.standard.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public @Data class Categoria implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String nome;
	private String descricao;
	private List<SubCategoria> subCategorias;

}
