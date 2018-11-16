package com.standard.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@ApiModelProperty(value = "codigo")
	private Long codigo;

	@ApiModelProperty(value = "nome", required = true)
	private String nome;

	@ApiModelProperty(value = "descricao", required = true)
	private String descricao;

	@ApiModelProperty(value = "subCategorias")
	private List<SubCategoria> subCategorias;

}
