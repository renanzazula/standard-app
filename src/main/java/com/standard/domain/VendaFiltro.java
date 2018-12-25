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
public class VendaFiltro implements Serializable {


    private static final long serialVersionUID = -6566111624858515644L;

    private Long codigo;

    @DateTimeFormat(pattern = Constants.PATTERN_DATE_FORMAT)
    private Date data;

    private String status;
    private Cliente cliente;
    private FormasDePagamento formaDePagamento;

}
