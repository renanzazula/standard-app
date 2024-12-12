package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public @Data class Pos implements Serializable {

	@Serial
	private static final long serialVersionUID = -6612762288260227887L;

	private Long id;

	@JsonFormat(pattern=Constants.PATTERN_DATE_FORMAT)
	private Date openDate;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private Date openTime;

	@JsonFormat(pattern=Constants.PATTERN_DATE_FORMAT)
	private Date closeDate;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private Date closeTime;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double openAmount;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double closeAmount;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double total;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double totalOrders;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double totalDiscount;
	 
	private String status;

}
