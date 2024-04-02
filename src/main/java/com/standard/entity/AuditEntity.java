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
public class  AuditEntity implements Serializable {

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", updatable = false)
    private Date creationDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    @Column(name = "creation_time", updatable = false)
    private Date creationTime;

    @CreatedBy
    @Column(name = "created_by", insertable = true, updatable = false)
    private Long createdBy;

    @Version
    @Column(name = "version")
    private Long version;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

}
