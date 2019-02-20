package com.standard.entity;

import com.standard.enums.StatusEnum;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "categoria_has_subcategoria", joinColumns = {
			@JoinColumn(name = "categoria_codigo", nullable = false, updatable = false, referencedColumnName = "codigo") }, inverseJoinColumns = {
					@JoinColumn(name = "subcategoria_codigo", nullable = false, updatable = false) })
	@OrderBy("codigo")
	private Set<SubcategoriaEntity> subcategoriasSet;



}
