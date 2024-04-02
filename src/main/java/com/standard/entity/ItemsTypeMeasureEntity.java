package com.standard.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "items_type_measure")
@EqualsAndHashCode(exclude="produtoHasItensTipoMedida")
public @Data class ItemsTypeMeasureEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@Column(name = "valor")
	private String valor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "measure_id")
	private MeasureEntity medida;

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
	@JoinColumn(name = "itens_tipo_medida_id")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;
 
}
