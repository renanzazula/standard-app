package com.standard.entity;

import com.standard.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "measure")
public class MeasureEntity extends BaseAuditEntity {

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "measure_id")
    @OrderBy("id")
    private Set<ItemsTypeMeasureEntity> itemsTypeMeasure;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        MeasureEntity that = (MeasureEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && status == that.status;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), name, description, status);
    }
}
