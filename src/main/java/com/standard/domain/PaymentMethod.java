package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod implements Serializable {


	@Serial
	private static final long serialVersionUID = 8567703800947910628L;

	private Long id;
	private String name;
	private String status;
	private String description;
	private Integer discountPercent;

    @JsonFormat(pattern=Constants.PATTERN_DATE_FORMAT)
	private OffsetDateTime creationDate;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private OffsetDateTime creationTime;
}
