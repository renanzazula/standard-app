package com.standard.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "items_type_measure")
@EqualsAndHashCode(exclude="productHasItemsTypeMeasure", callSuper = false)
public @Data class ItemsTypeMeasureEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@Column(name = "amount")
	private String amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "measure_id")
	private MeasureEntity measure;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id")
	private BrandEntity brand;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subcategory_id")
	private SubcategoryEntity subcategory;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "items_type_measure_id")
	private Set<ProductHasItemsTypeMeasureEntity> productHasItemsTypeMeasure;
 
}
