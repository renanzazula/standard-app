package com.standard.service.withdrawal;

import com.standard.domain.Withdrawal;

import java.util.List;

public interface WithdrawalService {

    Withdrawal create(Withdrawal obj);

    Withdrawal update(Long id, Withdrawal obj);

    Withdrawal findById(Long id);

    List<Withdrawal> findAll();

    void delete(Long id);
}
