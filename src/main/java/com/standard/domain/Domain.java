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
public class Domain implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4933949406995695753L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "nome", required = true)
    private String name;

    @ApiModelProperty(value = "description", required = true)
    private String description;

    @ApiModelProperty(value = "ativo", notes = "ativo = true, nao ativo = false", required = true)
    private boolean checked;

    @JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
    private Date data;

    @JsonFormat(pattern=Constants.PATTERN_TIME_FORMAT)
    private Date hora;

    private String status;
}
