package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;
	private Long codigo;
	private String nome;
	private String descricao;
	private List<Subcategoria> subcategorias;

	@JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
	private Date data;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private Date hora;
}
