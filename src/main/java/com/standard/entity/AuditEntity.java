package com.standard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity implements Serializable {

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private OffsetDateTime creationDate;

    @CreationTimestamp
    @Column(name = "creation_time", updatable = false)
    private OffsetDateTime creationTime;

    @CreatedBy
    @Column(name = "created_by", insertable = true, updatable = false)
    private Long createdBy;

    @Version
    @Column(name = "version")
    private Long version;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuditEntity that = (AuditEntity) o;
        return Objects.equals(creationDate, that.creationDate) && Objects.equals(creationTime, that.creationTime) && Objects.equals(createdBy, that.createdBy) && Objects.equals(
                version, that.version) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(lastModifiedBy, that.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, creationTime, createdBy, version, lastModifiedDate, lastModifiedBy);
    }
}
