package com.standard.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(exclude = "itensTipoMedida")
@Entity(name = "medida")
public @Data
class MedidaEntity extends BaseAuditEntity {

    private static final long serialVersionUID = -6612762288260227887L;

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

    // FIXME:
//	public MedidaEntity(Long codigo) {
//		super();
//		this.codigo = codigo;
//	}

    public MedidaEntity(String nome, String descricao) {
        super();
        this.nome = nome;
        this.descricao = descricao;
    }

}
