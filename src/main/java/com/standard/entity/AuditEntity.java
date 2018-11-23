package com.standard.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity implements Serializable {

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "data_criacao", updatable = false)
    private Date dataCriacao;

    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_criacao", updatable = false)
    private Date horaCriacao;

    @CreatedBy
    @Column(name = "criado_por", insertable = true, updatable = false)
    private Long criadorPor;

    @Version
    @LastModifiedDate
    @Column(name = "data_alteracao")
    private Date dataAlteracao;

    @LastModifiedBy
    @Column(name = "alterado_por")
    private Long alteradoPor;

}
