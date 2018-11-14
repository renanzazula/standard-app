package com.standard.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public @Data class Retirada implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long codigo;
	private String descricao;
	private Date dataHora;
	private Double valor;
	private Caixa caixa;
 
}
