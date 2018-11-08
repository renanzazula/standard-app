package com.standard.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public @Data class ProdutoHasItensTipoMedida implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private List<Dominio> dominios;
	private Integer quantidade;
	private Double  valorUnitario;
	private ItensTipoMedida itensTipoMedida;
	private Produto produto;

	 

}
