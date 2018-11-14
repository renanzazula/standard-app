package com.standard.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "categoria")
public @Data class CategoriaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;

	@NotNull
	@Size
	@Column(name = "nome", length = 45)
	private String nome;

	@Column(name = "descricao", length = 45)
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "categoria_has_sub_categoria", joinColumns = {
			@JoinColumn(name = "categoria_codigo", nullable = false, updatable = false, referencedColumnName = "codigo") }, inverseJoinColumns = {
					@JoinColumn(name = "sub_categoria_codigo", nullable = false, updatable = false) })
	@OrderBy("codigo")
	private Set<SubCategoriaEntity> subCategoriasSet;

	public CategoriaEntity() {

	}
	
	public CategoriaEntity(Long codigo) {
		super();
		this.codigo = codigo;
	}

	public CategoriaEntity(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	 

}
