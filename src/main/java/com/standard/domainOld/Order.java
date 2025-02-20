package com.standard.domainOld;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.enums.OrderStatusEnum;
import com.standard.util.Constants;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class Order implements Serializable {

	@Serial
	private static final long serialVersionUID = -6612762288260227887L;

	private Long id;

	@JsonFormat(pattern=Constants.PATTERN_DATE_FORMAT)
	private OffsetDateTime creationDate;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private OffsetDateTime creationTime;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double totalAmount;

	@NumberFormat(style=Style.NUMBER)
	private Integer quantity;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double subTotal;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double pendingAmount;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double paidAmount;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double discount;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double totalAmountToPaid;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double change;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double payment;

	private OrderStatusEnum status;
	private Pos pos;
	private Customer customer;

	private List<Product> products;
	private PaymentMethod paymentMethod;
	private List<PaymentMethod> paymentMethodList;
	private List<OrderHasItemProduct> orderHasItemProduct;
	
	public Order(Long id) {
		this.id = id;
	}
	
	public Order() {
	}
}
