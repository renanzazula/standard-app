package com.standard.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
//@EqualsAndHashCode(callSuper = false)
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuditEntity that = (AuditEntity) o;
        return Objects.equals(creationDate, that.creationDate) && Objects.equals(creationTime, that.creationTime) && Objects.equals(createdBy, that.createdBy) && Objects.equals(
                version, that.version) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(lastModifiedBy, that.lastModifiedBy);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(creationDate, creationTime, createdBy, version, lastModifiedDate, lastModifiedBy);
    }
}
