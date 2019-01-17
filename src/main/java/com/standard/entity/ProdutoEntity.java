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
     * codigo -> sequencial (from dataBase) marca_codigo fornecedor_codigo
     * categoria_codigo subcategoria_codigo medida_codigo flagSite -> : LFB -> loja
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

    @NotNull
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
    @JoinColumn(name = "marca_codigo", updatable = false)
    private MarcaEntity marca;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fornecedor_codigo")
    private FornecedorEntity fornecedor;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_codigo")
    private CategoriaEntity categoria;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medida_codigo")
    private MedidaEntity medida;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcategoria_codigo")
    private SubcategoriaEntity subcategoria;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_codigo")
    @OrderBy("itensTipoMedida")
    private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;

}
