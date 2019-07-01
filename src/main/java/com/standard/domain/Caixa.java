package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;
import java.util.Date;

public @Data class Caixa implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long codigo;

	@JsonFormat(pattern=Constants.PATTERN_DATE_FORMAT)
	private Date dataAbertura;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private Date horaAbertura;

	@JsonFormat(pattern=Constants.PATTERN_DATE_FORMAT)
	private Date dataFechamento;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
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
