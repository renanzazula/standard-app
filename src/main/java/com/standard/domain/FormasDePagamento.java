package com.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormasDePagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;
	private String descricao;
	private Integer porcentagemDesconto;

}
