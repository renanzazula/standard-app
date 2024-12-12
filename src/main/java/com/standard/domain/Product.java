package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.enums.StatusEnum;
import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public   class Product implements Serializable {

	@Serial
	private static final long serialVersionUID = -6612762288260227887L;

	private Long id;
	private String barCode;
	private String name;
	private String description;
	private StatusEnum status;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double price;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double salePrice;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double costPrice;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double discountPrice;
	
	private Double discount;
	private Double weight;
	
	@NumberFormat(style=Style.PERCENT)
	private Integer percent;
	
	@NumberFormat(style=Style.PERCENT)
	private Integer discountPercent;

	@JsonFormat(pattern=Constants.PATTERN_DATE_TIME_FORMAT)
	private Date creationDateTime;

	private Provider provider;
	private Measure measure;
	private Category category;
	private Subcategory subcategory;
	private Brand brand;

	private List<Domain> domains;
	private List<Provider> providers;
	private List<Category> categories;
	private List<Subcategory> subcategories;
	private List<Brand> brands;
	private List<Measure> measures;
	private List<ProductHasItemsTypeMeasure> productHasItemsTypeMeasure;
 	private Integer totalStockQuantity;
			
	public Integer getTotalStockQuantity() {
		int totalStockQuantity = 0;
		if(this.productHasItemsTypeMeasure != null) {
			for (ProductHasItemsTypeMeasure correnteProductHasItemsTypeMeasure : this.productHasItemsTypeMeasure) {
				if(correnteProductHasItemsTypeMeasure.getQuantity() != null) {
					totalStockQuantity = totalStockQuantity + correnteProductHasItemsTypeMeasure.getQuantity();
				}
			}
		}
		return totalStockQuantity;
	}
}
