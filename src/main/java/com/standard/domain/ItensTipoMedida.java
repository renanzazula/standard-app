package com.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensTipoMedida implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long codigo;
	private String valor;
	private Medida medida;
	private Marca marca;
	private Categoria categoria;
	private SubCategoria subCategoria;

}
