package com.standard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "items_type_measure")
//@EqualsAndHashCode(exclude="productHasItemsTypeMeasure", callSuper = false)
public class ItemsTypeMeasureEntity extends BaseAuditEntity {


	@Serial
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		ItemsTypeMeasureEntity that = (ItemsTypeMeasureEntity) o;
		return Objects.equals(amount, that.amount) && Objects.equals(measure, that.measure) && Objects.equals(brand, that.brand) && Objects.equals(category,
				that.category) && Objects.equals(subcategory, that.subcategory);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), amount, measure, brand, category, subcategory);
	}
}
