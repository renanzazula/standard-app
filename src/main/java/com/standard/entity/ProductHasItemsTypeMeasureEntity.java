package com.standard.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(exclude = {"domains", "vendaHasItemProduto" })
@Entity(name = "product_has_items_type_measure")
public @Data
class ProductHasItemsTypeMeasureEntity extends BaseAuditEntity {

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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "product_has_items_type_measure_has_domain", joinColumns = {
            @JoinColumn(name = "product_has_items_type_measure_id")}, inverseJoinColumns = {
            @JoinColumn(name = "domain_id")})
    private Set<DomainEntity> domains;

    @OneToMany(mappedBy = "productHasItemsTypeMeasure", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderHasItemProductEntity> orderHasItemProduct;


}
