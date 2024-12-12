package com.standard.domain;

import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHasItemProduct implements Serializable {


	@Serial
	private static final long serialVersionUID = -6408847193452580066L;
	
	private ProductHasItemsTypeMeasure productHasItemsTypeMeasure;
 	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorUnitario;
	
	@NumberFormat(style=Style.NUMBER)
 	private Integer quantidade;
 	
	public ProductHasItemsTypeMeasure getProductHasItemsTypeMeasure() {
		return productHasItemsTypeMeasure;
	}
	public void setProductHasItemsTypeMeasure(ProductHasItemsTypeMeasure productHasItemsTypeMeasure) {
		this.productHasItemsTypeMeasure = productHasItemsTypeMeasure;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

 	
 
	
}
