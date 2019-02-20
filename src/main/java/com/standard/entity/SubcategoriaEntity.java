package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(exclude = "categoria")
@Entity(name = "subcategoria")
public @Data class SubcategoriaEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@NotNull
	@Column(name = "nome", length = 45)
	private String nome;

	@NotNull
	@Column(name = "descricao", length = 45)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "subcategoriasSet")
	private Set<CategoriaEntity> categoria;

}
