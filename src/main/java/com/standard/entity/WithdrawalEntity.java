package com.standard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "withdrawal")
public @Data class WithdrawalEntity extends BaseAuditEntity {

	@Serial
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		WithdrawalEntity that = (WithdrawalEntity) o;
		return Objects.equals(description, that.description) && Objects.equals(amount, that.amount) && Objects.equals(pos, that.pos);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), description, amount, pos);
	}
}
