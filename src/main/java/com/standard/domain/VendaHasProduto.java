package com.standard.domain;

import com.standard.util.Constants;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;

public @Data class VendaHasProduto implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;
	private Integer codigo;
	private Venda venda;
	private Produto produto;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorUnitario;
	
	//FIXME: validar se necessario 
	private Integer quantidade;

}
