package com.standard.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "dominio")
public @Data class DominioEntity extends BaseAuditEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4933949406995695753L;

	@NotNull
	@Column(name = "nome", length = 45)
	private String nome;

	@NotNull
	@Column(name = "descricao", length = 45)
	private String descricao;

	@ManyToMany(mappedBy = "dominios")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida; 

}
