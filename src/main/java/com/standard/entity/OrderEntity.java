package com.standard.entity;

import com.standard.enums.StatusVendaEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = "orderHasItemProduct")
@Entity(name = "order")
public @Data class OrderEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@CreationTimestamp
	@Column(name = "creationDate")
	private LocalDateTime creationDate;

	@CreationTimestamp
	@Column(name = "creationTime")
	private LocalDateTime creationTime;

	@Column(name = "totalAmount")
	private Double totalAmount;

	@Column(name = "subTotal")
	private Double subTotal;

	@Column(name = "pendingAmount")
	private Double pendingAmount;

	@Column(name = "paidAmount")
	private Double paidAmount;

	@Column(name = "discount")
	private Double discount;

	@Column(name = "totalAmountToPaid")
	private Double totalAmountToPaid;

	@Column(name = "change")
	private Double change;

	@Column(name = "payment")
	private Double payment;

	@NotNull
	@Column(name = "quantity")
	private Integer quantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusVendaEnum status;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "pos_id")
	private PosEntity pos;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "paymentMethod_id")
	private PaymentMethodEntity paymentMethod;

	@NotNull
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderHasItemProductEntity> orderHasItemProduct = new HashSet<>();

}
