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

@Entity(name = "produto")
@EqualsAndHashCode(exclude = "produtoHasItensTipoMedida")
public @Data class ProdutoEntity extends BaseAuditEntity {

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
    @Column(name = "nome", length = 45)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @NotNull
    @Column(name = "descricao", length = 45)
    private String descricao;

//    @NotNull TODO: list de prices base on configuration
    @Column(name = "preco")
    private Double preco;

    @NotNull
    @Column(name = "precoVenda")
    private Double precoVenda;

    @NotNull
    @Column(name = "precoCusto")
    private Double precoCusto;

    @NotNull
    @Column(name = "precoOferta")
    private Double precoOferta;

    @NotNull
    @Column(name = "desconto")
    private Double desconto;

    @NotNull
    @Column(name = "peso")
    private Double peso;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "porcentagem")
    private Integer porcentagem;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "porcentagemDesconto")
    private Integer porcentagemDesconto;

    @Lob
    @Column(name = "foto", columnDefinition = "BLOB")
    private byte[] foto;

    // fixme: data hora separar
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataHoraCadastro")
    private Date dataHoraCadastro;

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
    @JoinColumn(name = "produto_id")
    private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;

}
