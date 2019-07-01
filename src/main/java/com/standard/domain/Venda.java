package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.enums.StatusVendaEnum;
import com.standard.util.Constants;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Venda implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long codigo;

	// @JsonFormat(pattern=Constants.PATTERN_DATE_FORMAT)
	private LocalDateTime data;

	// @JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
	private LocalDateTime hora;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorTotal;

	@NumberFormat(style=Style.NUMBER)
	private Integer quantidade;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double subTotal;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorPendente;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorPago;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double desconto;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double totalApagar;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double troco;

	@JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double pagamento;

	private StatusVendaEnum status;
	private Caixa caixa;
	private Cliente cliente;

	private List<Produto> produtos;
	private FormasDePagamento formaDePagamento;
	private List<FormasDePagamento> formasDePagamento;
	private List<VendaHasItemProduto> vendaHasItemProduto;
	
	public Venda(Long codigo) {
		this.codigo = codigo;
	}
	
	public Venda() {		 
	}
}
