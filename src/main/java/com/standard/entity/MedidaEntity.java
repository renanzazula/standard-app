package com.standard.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(exclude= "itensTipoMedida")
@Entity(name = "medida")
public @Data class MedidaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@NotNull
	@Column(name = "nome", length = 45)
	private String nome;

	@NotNull
	@Column(name = "descricao", length = 45)
	private String descricao;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "medida_codigo")
	@OrderBy("codigo")
	private Set<ItensTipoMedidaEntity> itensTipoMedida;

	public MedidaEntity() {

	}

	public MedidaEntity(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public MedidaEntity(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

}
