package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "paymentMethod")
public @Data class PaymentMethodEntity extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "name", length = 45)
	private String name;

	@NotNull
	@Column(name = "description", length = 45)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@NotNull
	@Min(0)
	@Max(100)
	@Column(name = "discountPercent")
	private int discountPercent;

}
