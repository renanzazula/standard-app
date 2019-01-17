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
	@JoinColumn(name = "medida_codigo")
	private MedidaEntity medida;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "marca_codigo")
	private MarcaEntity marca;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_codigo")
	private CategoriaEntity categoria;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subcategoria_codigo")
	private SubcategoriaEntity subcategoria;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "itens_tipo_medida_codigo")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;
 
}
