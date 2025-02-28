package com.standard.service.payback;

import com.standard.domain.PayBack;

import java.util.List;

public interface PayBackService {

    PayBack create(PayBack objct);

    PayBack update(Long id, PayBack objct);

    void delete(Long id);

    List<PayBack> findAll();

    PayBack findById(Long id);
}
