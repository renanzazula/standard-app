package com.standard.domainOld;

import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderHasProduct implements Serializable {

	@Serial
	private static final long serialVersionUID = -6612762288260227887L;
	private Long id;
	private Order order;
	private Product product;
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double unitValue;
	private Integer quantity;

}
