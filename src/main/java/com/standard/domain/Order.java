package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.enums.OrderStatusEnum;
import com.standard.util.Constants;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long id;

	//@JsonFormat(pattern=Constants.PATTERN_DATE_FORMAT)
	private LocalDateTime creationDate;

	// @JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private LocalDateTime creationTime;

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
	private PaymentMethod formaDePagamento;
	private List<PaymentMethod> paymentMethod;
	private List<OrderHasItemProduct> orderHasItemProduct;
	
	public Order(Long id) {
		this.id = id;
	}
	
	public Order() {
	}
}
