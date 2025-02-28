package com.standard.entity;

import com.standard.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class ProductEntity extends BaseAuditEntity
{

	@Serial
	private static final long serialVersionUID = 2203862074139518315L;

	/**
	 * id -> sequencial (from dataBase) marca_id fornecedor_id categoria_id subcategoria_id medida_id flagSite -> : LFB -> lojaz fisica born : LOW -> loja online Wix
	 */
	@NotNull
	@Column(name = "barCode")
	private String barCode;

	@NotNull
	@Column(name = "name", length = 45)
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@NotNull
	@Column(name = "description", length = 45)
	private String description;

  	@NotNull
	@Column(name = "price")
	private Double price;

	@NotNull
	@Column(name = "salePrice")
	private Double salePrice;

	@NotNull
	@Column(name = "costPrice")
	private Double costPrice;

	@NotNull
	@Column(name = "discountPrice")
	private Double discountPrice;

	@NotNull
	@Column(name = "discount")
	private Double discount;

	@NotNull
	@Column(name = "weight")
	private Double weight;

	@NotNull
	@Min(0)
	@Max(100)
	@Column(name = "percent")
	private Integer percent;

	@NotNull
	@Min(0)
	@Max(100)
	@Column(name = "discountPercent")
	private Integer discountPercent;

	@Lob
	@Column(name = "photo", columnDefinition = "BLOB")
	private byte[] photo;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "brand_id", updatable = false)
	private BrandEntity brand;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "provider_id")
	private ProviderEntity provider;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "measure_id")
	private MeasureEntity measure;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subcategory_id")
	private SubcategoryEntity subcategory;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
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
		ProductEntity that = (ProductEntity) o;
		return Objects.equals(barCode, that.barCode) && Objects.equals(name, that.name) && status == that.status && Objects.equals(description, that.description) && Objects.equals(
				price, that.price) && Objects.equals(salePrice, that.salePrice) && Objects.equals(costPrice, that.costPrice) && Objects.equals(discountPrice,
				that.discountPrice) && Objects.equals(discount, that.discount) && Objects.equals(weight, that.weight) && Objects.equals(percent, that.percent) && Objects.equals(
				discountPercent, that.discountPercent) && Objects.deepEquals(photo, that.photo)  && Objects.equals(brand,
				that.brand) && Objects.equals(provider, that.provider) && Objects.equals(category, that.category) && Objects.equals(measure, that.measure) && Objects.equals(
				subcategory, that.subcategory);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), barCode, name, status, description, price, salePrice, costPrice, discountPrice, discount, weight, percent, discountPercent,
				Arrays.hashCode(photo), brand, provider, category, measure, subcategory);
	}

}
