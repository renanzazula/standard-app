package com.standard.service.withdrawal;

import com.standard.domain.Withdrawal;
import com.standard.entity.WithdrawalEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.PosRepository;
import com.standard.repository.WithdrawalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class WithdrawalServiceImpl implements WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;
    private final PosRepository posRepository;

    @Override
    public Withdrawal create(Withdrawal obj) {
        WithdrawalEntity withdrawalDB = new WithdrawalEntity();
        withdrawalDB.setDescription(obj.getDescription());
        withdrawalDB.setAmount(obj.getAmount());
        withdrawalDB.setPos(posRepository.getById(obj.getPos().getId()));
        return JpaFunctions.withdrawalEntityToWithdrawal.apply(withdrawalRepository.saveAndFlush(withdrawalDB));
    }

    @Override
    public Withdrawal update(Long id, Withdrawal obj) {
        WithdrawalEntity withdrawalDB = withdrawalRepository.getById(id);
        withdrawalDB.setDescription(obj.getDescription());
        withdrawalDB.setAmount(obj.getAmount());
        withdrawalDB.setPos(posRepository.getById(obj.getPos().getId()));
        return JpaFunctions.withdrawalEntityToWithdrawal.apply(withdrawalRepository.saveAndFlush(withdrawalDB));
    }

    @Override
    public Withdrawal findById(Long id) {
        return JpaFunctions.withdrawalEntityToWithdrawal.apply(withdrawalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Withdrawal não encontrado!")));
    }

    @Override
    public List<Withdrawal> findAll() {
        return withdrawalRepository.findAll().stream().map(JpaFunctions.withdrawalEntityToWithdrawal).toList();
    }

    @Override
    public void delete(Long id) {
        withdrawalRepository.deleteById(id);
    }
}
