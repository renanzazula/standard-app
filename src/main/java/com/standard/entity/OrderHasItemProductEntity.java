package com.standard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_has_item_product")
public class OrderHasItemProductEntity extends BaseAuditEntity{

	@Serial
	private static final long serialVersionUID = -2516119080969832005L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_has_item_product_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_has_items_items_type_measure_id", updatable = false)
	@OrderBy("id")
	private ProductHasItemsTypeMeasureEntity productHasItemsTypeMeasure;

	@Column(name = "unit_value")
	private Double unitValue;

	@Column(name = "quantity")
	private Integer quantity;

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		OrderHasItemProductEntity that = (OrderHasItemProductEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(order, that.order) && Objects.equals(productHasItemsTypeMeasure, that.productHasItemsTypeMeasure) && Objects.equals(
				unitValue, that.unitValue) && Objects.equals(quantity, that.quantity);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), id, order, productHasItemsTypeMeasure, unitValue, quantity);
	}

}
