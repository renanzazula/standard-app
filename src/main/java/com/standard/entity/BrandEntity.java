package com.standard.entity;

import com.standard.enums.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "brand")
public class BrandEntity extends BaseAuditEntity {

    @Serial
    private static final long serialVersionUID = -6612762288260227887L;

    @NotNull
    @Column(name = "name", length = 45)
    private String name;

    @NotNull
    @Column(name = "description", length = 45)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BrandEntity that = (BrandEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && status == that.status;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), name, description, status);
    }
}
