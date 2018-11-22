package com.standard.entity;

import com.standard.enums.StatusCaixaEnum;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "caixa")
public @Data class CaixaEntity  extends BaseAuditEntity  {

	private static final long serialVersionUID = -6612762288260227887L;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "dataAbertura")
	private Date dataAbertura;
	 
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "horaAbertura")
	private Date horaAbertura;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataFechamento")
	private Date dataFechamento;

	@Temporal(TemporalType.TIME)
	@Column(name = "horaFechamento")
	private Date horaFechamento;

	@NotNull
	@ColumnDefault(value = "0")
	@Column(name = "valorInicial")
	private Double valorInicial;

	@ColumnDefault(value = "0")
	@Column(name = "valorFinal")
	private Double valorFinal;

	@ColumnDefault(value = "0")
	@Column(name = "totalVendas")
	private Double totalVendas;

	@ColumnDefault(value = "0")
	@Column(name = "total")
	private Double total;

	@ColumnDefault(value = "0")
	@Column(name = "totalDesconto")
	private Double totalDesconto;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusCaixaEnum status;

 
}
