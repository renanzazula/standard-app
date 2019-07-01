package com.standard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.util.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dominio implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4933949406995695753L;

    @ApiModelProperty(value = "codigo")
    private Long codigo;

    @ApiModelProperty(value = "nome", required = true)
    private String nome;

    @ApiModelProperty(value = "descricao", required = true)
    private String descricao;

    @ApiModelProperty(value = "ativo", notes = "ativo = true, nao ativo = false", required = true)
    private boolean checked;

    @JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
    private Date data;

    @JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
    private Date hora;
}
