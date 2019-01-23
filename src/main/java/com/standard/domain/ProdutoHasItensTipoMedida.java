package com.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
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


}
