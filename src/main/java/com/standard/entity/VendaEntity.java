package com.standard.entity;

import com.standard.enums.StatusVendaEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = "vendaHasItemProduto")
@Entity(name = "venda")
public @Data class VendaEntity extends BaseAuditEntity {

	private static final long serialVersionUID = -6612762288260227887L;

	@CreationTimestamp
	@Column(name = "data")
	private LocalDateTime data;

	@CreationTimestamp
	@Column(name = "hora")
	private LocalDateTime hora;

	@Column(name = "valorTotal")
	private Double valorTotal;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusVendaEnum status;

	@Column(name = "subTotal")
	private Double subTotal;

	@Column(name = "valorPendente")
	private Double valorPendente;

	@Column(name = "valorPago")
	private Double valorPago;

	@Column(name = "desconto")
	private Double desconto;

	@Column(name = "totalApagar")
	private Double totalApagar;

	@Column(name = "troco")
	private Double troco;

	@Column(name = "pagamento")
	private Double pagamento;

	@NotNull
	@Column(name = "quantidade")
	private Integer quantidade;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "caixa_codigo")
	private CaixaEntity caixa;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_codigo")
	private ClienteEntity cliente;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "formasDePagamento_codigo")
	private FormaDePagamentoEntity formaDePagamento;

	@NotNull
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private Set<VendaHasItemProdutoEntity> vendaHasItemProduto = new HashSet<>();

}
