package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayBack implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long id;
	private String name;
	private String description;
	@JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
	private Date data;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private Date hora;

	private Double valor;
	private Pos pos;
	private Customer customer;

}
