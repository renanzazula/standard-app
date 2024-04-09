package com.standard.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "payback")
public @Data class PayBackEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "amount")
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "pos_id")
	private PosEntity pos;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
}
