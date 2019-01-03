package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity(name = "marca")
public @Data
class MarcaEntity extends BaseAuditEntity {

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

}
