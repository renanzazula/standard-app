package com.standard.service.payback;

import com.standard.domain.Recebimento;

import java.util.List;

public interface PayBackService {

    Recebimento create(Recebimento objct);

    Recebimento update(Long id, Recebimento objct);

    void delete(Long id);

    List<Recebimento> findAll();

    Recebimento getById(Long id);
}
