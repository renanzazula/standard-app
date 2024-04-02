package com.standard.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "itens_tipo_medida")
@EqualsAndHashCode(exclude="produtoHasItensTipoMedida")
public @Data class ItensTipoMedidaEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@Column(name = "valor")
	private String valor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medida_id")
	private MedidaEntity medida;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "marca_id")
	private MarcaEntity marca;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private CategoryEntity categoria;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subcategory_id")
	private SubcategoryEntity subcategoria;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "itens_tipo_medida_id")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;
 
}
