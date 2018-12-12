package com.standard.domain;

import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormasDePagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;
	private String descricao;
	private Integer porcentagemDesconto;
	@DateTimeFormat(pattern = Constants.PATTERN_DATE_FORMAT)
	private Date data;
	@DateTimeFormat(pattern = Constants.PATTERN_TIME_FORMAT)
	private Date hora;
}
