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
public class PaymentMethod implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String status;
	private String description;
	private Integer discountPercent;

    @JsonFormat(pattern=Constants.PATTERN_DATE_FORMAT)
	private Date creationDate;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private Date creationTime;
}
