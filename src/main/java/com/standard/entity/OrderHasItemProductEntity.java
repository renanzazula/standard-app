package com.standard.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_has_item_product")
public @Data class OrderHasItemProductEntity extends BaseAuditEntity{

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

	 
}
