package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(exclude = "itemsTypeMeasure", callSuper = false)
@Entity(name = "measure")
public @Data
class MeasureEntity extends BaseAuditEntity {

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
}
