package com.standard.domain;

import com.standard.util.Constants;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public @Data class Venda implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;

	@DateTimeFormat(pattern = Constants.PATTERN_DATE_FORMAT)
	private Date data;

	@DateTimeFormat(pattern = Constants.PATTERN_TIME_FORMAT)
	private Date hora;

	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorTotal;
	
	@NumberFormat(style=Style.NUMBER)
	private Integer quantidade;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double subTotal;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorPendente;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorPago;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double desconto;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double totalApagar;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double troco;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double pagamento;

	private String status;
	private Caixa caixa;
	private Cliente cliente;

	private List<Produto> produtos;
	private FormasDePagamento formaDePagamento;
	private List<FormasDePagamento> formasDePagamento;
	private List<VendaHasItemProduto> vendaHasItemProduto;
	
	public Venda(Integer codigo) {		 
		this.codigo = codigo;
	}
	
	public Venda() {		 
	}
}
