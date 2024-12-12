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
import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payback")
public class PayBackEntity extends BaseAuditEntity {

	@Serial
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		PayBackEntity that = (PayBackEntity) o;
		return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount) && Objects.equals(pos,
				that.pos) && Objects.equals(customer, that.customer);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), name, description, amount, pos, customer);
	}
}
