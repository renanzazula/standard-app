package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoHasItensTipoMedida implements Serializable {

    private static final long serialVersionUID = -6612762288260227887L;

    private Long codigo;
    private List<Dominio> dominios;
    private Integer quantidade;
    private Double valorUnitario;
    private ItensTipoMedida itensTipoMedida;
    private Produto produto;
    @JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
    private Date data;

    @JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
    private Date hora;

}
