package com.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Retirada implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long codigo;
	private String descricao;
	//fixme:
	private Date data;
	private Date hora;
	private Double valor;
	private Caixa caixa;
 
}
