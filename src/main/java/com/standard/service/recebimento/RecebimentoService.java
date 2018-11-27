package com.standard.service.recebimento;

import com.standard.domain.Recebimento;

import java.util.List;

public interface RecebimentoService {

    Recebimento incluir(Recebimento objct);

    Recebimento alterar(Long codigo, Recebimento objct);

    void excluir(Long codigo);

    List<Recebimento> consultar();

    Recebimento consultarByCodigo(Long codigo);
}
