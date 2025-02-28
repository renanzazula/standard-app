package com.standard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product_has_items_type_measure")
public class ProductHasItemsTypeMeasureEntity extends BaseAuditEntity {

    @Serial
    private static final long serialVersionUID = -6612762288260227887L;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_value")
    private Double unitValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "items_type_measure_id", updatable = false)
    private ItemsTypeMeasureEntity itemsTypeMeasure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "product_has_items_type_measure_has_domain", joinColumns = {
            @JoinColumn(name = "product_has_items_type_measure_id")}, inverseJoinColumns = {
            @JoinColumn(name = "domain_id")})
    private Set<DomainEntity> domains;

    @OneToMany(mappedBy = "productHasItemsTypeMeasure", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderHasItemProductEntity> orderHasItemProduct;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ProductHasItemsTypeMeasureEntity that = (ProductHasItemsTypeMeasureEntity) o;
        return Objects.equals(quantity, that.quantity) && Objects.equals(unitValue, that.unitValue) && Objects.equals(itemsTypeMeasure, that.itemsTypeMeasure) && Objects.equals(
                product, that.product);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), quantity, unitValue, itemsTypeMeasure, product);
    }
}
