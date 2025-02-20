package com.standard.domainOld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemsTypeMeasure implements Serializable {

	@Serial
	private static final long serialVersionUID = -6612762288260227887L;

	private Long id;
	private String amount;
	private Measure measure;
	private Brand brand;
	private Category category;
	private Subcategory subcategory;

}
