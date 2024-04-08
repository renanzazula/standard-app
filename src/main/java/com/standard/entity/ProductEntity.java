package com.standard.entity;

import com.standard.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity(name = "product")
@EqualsAndHashCode(exclude = "productHasItemsTypeMeasure")
public @Data class ProductEntity extends BaseAuditEntity {

    private static final long serialVersionUID = 2203862074139518315L;

    /**
     * id -> sequencial (from dataBase) marca_id fornecedor_id
     * categoria_id subcategoria_id medida_id flagSite -> : LFB -> loja
     * fisica born : LOW -> loja online Wix
     */
    @NotNull
    @Column(name = "barCode")
    private String barCode;

    @NotNull
    @Column(name = "name", length = 45)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @NotNull
    @Column(name = "description", length = 45)
    private String description;

//    @NotNull TODO: list de prices base on configuration
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "salePrice")
    private Double salePrice;

    @NotNull
    @Column(name = "costPrice")
    private Double costPrice;

    @NotNull
    @Column(name = "discountPrice")
    private Double discountPrice;

    @NotNull
    @Column(name = "discount")
    private Double discount;

    @NotNull
    @Column(name = "weight")
    private Double weight;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "percent")
    private Integer percent;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "discountPercent")
    private Integer discountPercent;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

    // fixme: data hora separar
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDateTime")
    private Date creationDateTime;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", updatable = false)
    private BrandEntity brand;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id")
    private ProviderEntity provider;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "measure_id")
    private MeasureEntity measure;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcategory_id")
    private SubcategoryEntity subcategory;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<ProductHasItemsTypeMeasureEntity> productHasItemsTypeMeasure;

}
