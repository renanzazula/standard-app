package com.standard.domain;

import com.standard.util.Constants;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;
import java.util.Date;

public @Data class Caixa implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	
	@DateTimeFormat(pattern = Constants.PATTERN_DATE_FORMAT)
	private Date dataAbertura;
	
	@DateTimeFormat(pattern = Constants.PATTERN_TIME_FORMAT)
	private Date horaAbertura;
	
	@DateTimeFormat(pattern = Constants.PATTERN_DATE_FORMAT)
	private Date dataFechamento;
	
	@DateTimeFormat(pattern = Constants.PATTERN_TIME_FORMAT)
	private Date horaFechamento;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorInicial;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorFinal;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double total;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double totalVendas;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double totalDesconto;
	 
	private String status;

}
