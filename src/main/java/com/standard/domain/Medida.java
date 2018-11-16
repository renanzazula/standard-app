package com.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medida implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long codigo;
	private String nome;
	private String descricao;
	
	private Categoria categoria;
	private SubCategoria subCategoria;
	private Marca marca;
	
	private List<Categoria> categorias;
	private List<SubCategoria> subCategorias;
	private List<Marca> marcas;
	
	private List<ItensTipoMedida> itensTipoMedida;

	 

	
	
}
