package com.standard.entity;

import com.standard.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order")
public   class OrderEntity extends BaseAuditEntity {

	@Serial
	private static final long serialVersionUID = -6612762288260227887L;

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

	@Column(name = "changing")
	private Double changing;

	@Column(name = "payment")
	private Double payment;

	@NotNull
	@Column(name = "quantity")
	private Integer quantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private OrderStatusEnum status;

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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		OrderEntity that = (OrderEntity) o;
		return Objects.equals(totalAmount, that.totalAmount) && Objects.equals(subTotal, that.subTotal) && Objects.equals(pendingAmount, that.pendingAmount) && Objects.equals(
				paidAmount, that.paidAmount) && Objects.equals(discount, that.discount) && Objects.equals(totalAmountToPaid, that.totalAmountToPaid) && Objects.equals(changing,
				that.changing) && Objects.equals(payment, that.payment) && Objects.equals(quantity, that.quantity) && status == that.status && Objects.equals(pos,
				that.pos) && Objects.equals(customer, that.customer) && Objects.equals(paymentMethod, that.paymentMethod);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), totalAmount, subTotal, pendingAmount, paidAmount, discount, totalAmountToPaid, changing, payment, quantity, status, pos, customer,
				paymentMethod);
	}
}
