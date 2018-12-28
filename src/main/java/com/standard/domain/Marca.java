package com.standard.domain;

import com.standard.enums.StatusEnum;
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
public class Marca implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Long codigo;
	private String nome;
	private String descricao;
	private StatusEnum status;
	@DateTimeFormat(pattern = Constants.PATTERN_DATE_FORMAT)
	private Date data;
	@DateTimeFormat(pattern = Constants.PATTERN_TIME_FORMAT)
	private Date hora;
	@DateTimeFormat(pattern = Constants.PATTERN_DATE_FORMAT)
	private Date dataAlteracao;
	@DateTimeFormat(pattern = Constants.PATTERN_TIME_FORMAT)
	private Date horaAlteracao;


}
