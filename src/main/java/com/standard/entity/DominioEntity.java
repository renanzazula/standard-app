package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@ManyToMany(mappedBy = "dominios")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida; 

}
