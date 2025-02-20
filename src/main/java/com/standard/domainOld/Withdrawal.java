package com.standard.domainOld;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Withdrawal implements Serializable {

	@Serial
	private static final long serialVersionUID = -6612762288260227887L;

	private Long id;
	private String description;

	@JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
	private OffsetDateTime creationDate;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private OffsetDateTime creationTime;

	private Double amount;
	private Pos pos;
 
}
