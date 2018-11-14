package com.standard.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(exclude = "categoria")
@Entity(name = "sub_categoria")
public @Data class SubCategoriaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;

	@NotNull
	@Column(name = "nome", length = 45)
	private String nome;

	@NotNull
	@Column(name = "descricao", length = 45)
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "subCategoriasSet")
	private Set<CategoriaEntity> categoria;

}
