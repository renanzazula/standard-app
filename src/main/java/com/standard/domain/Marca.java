package com.standard.domain;

import com.standard.enums.StatusEnum;
import com.standard.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;

public @Data class Marca implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String nome;
	private String descricao;
	private StatusEnum status;

	 

}
