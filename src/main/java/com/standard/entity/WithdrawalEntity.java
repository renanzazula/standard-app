package com.standard.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "withdrawal")
public @Data class WithdrawalEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@NotNull
	@Column(name = "description")
	private String description;

	@NotNull
	@Column(name = "amount")
	private Double amount;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "pos")
	private PosEntity pos;
}
