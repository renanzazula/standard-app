package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "paymentMethod")
public class PaymentMethodEntity extends BaseAuditEntity {

	@Serial
	private static final long serialVersionUID = -3606796441177890468L;

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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		PaymentMethodEntity that = (PaymentMethodEntity) o;
		return discountPercent == that.discountPercent && Objects.equals(name, that.name) && Objects.equals(description, that.description) && status == that.status;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), name, description, status, discountPercent);
	}
}
