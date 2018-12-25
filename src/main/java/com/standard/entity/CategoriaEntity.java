package com.standard.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name = "categoria")
public @Data class CategoriaEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@NotNull
	@Size(max = 45)
	@Column(name = "nome", length = 45)
	private String nome;

	@Size(max = 45)
	@Column(name = "descricao", length = 45)
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "categoria_has_sub_categoria", joinColumns = {
			@JoinColumn(name = "categoria_codigo", nullable = false, updatable = false, referencedColumnName = "codigo") }, inverseJoinColumns = {
					@JoinColumn(name = "sub_categoria_codigo", nullable = false, updatable = false) })
	@OrderBy("codigo")
	private Set<SubCategoriaEntity> subCategoriasSet;



}
