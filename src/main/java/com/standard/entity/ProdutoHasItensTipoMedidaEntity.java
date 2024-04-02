package com.standard.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(exclude = {"domains", "vendaHasItemProduto" })
@Entity(name = "produto_has_itens_tipo_medida")
public @Data
class ProdutoHasItensTipoMedidaEntity extends BaseAuditEntity {

    private static final long serialVersionUID = -6612762288260227887L;


    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "valor_unitario")
    private Double valorUnitario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itens_tipo_medida_id", updatable = false)
    private ItemsTypeMeasureEntity itensTipoMedida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "produto_has_itens_tipo_medida_has_dominio", joinColumns = {
            @JoinColumn(name = "produto_has_itens_tipo_medida_id")}, inverseJoinColumns = {
            @JoinColumn(name = "domain_id")})
    private Set<DomainEntity> domains;

    @OneToMany(mappedBy = "produtoHasItensTipoMedida", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<VendaHasItemProdutoEntity> vendaHasItemProduto;


}
