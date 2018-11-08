package com.standard.domain;

import lombok.Data;

import java.io.Serializable;

public @Data class ItensTipoMedida implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String valor;
	private Medida medida;
	private Marca marca;
	private Categoria categoria;
	private SubCategoria subCategoria;

}
