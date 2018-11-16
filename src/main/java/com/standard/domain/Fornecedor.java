package com.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long codigo;
	private String nome;
	private String descricao;

}
