package com.standard.service.retirada;

import com.standard.domain.Retirada;

import java.util.List;

public interface RetiradaService {

    Retirada incluir(Retirada obj);

    Retirada alterar(Long codigo, Retirada obj);

    Retirada consultarByCodigo(Long codigo);

    List<Retirada> consultar();

    void excluir(Long codigo);
}
