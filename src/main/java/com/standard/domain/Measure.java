package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Measure implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long id;
	private String nome;
	private String description;
	private String status;

	@JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
	private Date date;

	@JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private Date hora;

	private Category category;
	private Subcategory subcategory;
	private Brand brand;
	
	private List<Category> categories;
	private List<Subcategory> subcategories;
	private List<Brand> brands;
	private List<ItemsTypeMeasure> itemsTypeMeasure;

}
