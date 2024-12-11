package com.standard.service.payback;

import com.standard.domain.PayBack;
import com.standard.entity.PayBackEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.CustomerRepository;
import com.standard.repository.PayBackRepository;
import com.standard.repository.PosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class PayBackServiceImpl implements PayBackService {

    private final PayBackRepository payBackRepository;
    private final PosRepository posRepository;
    private final CustomerRepository customerRepository;

    @Override
    public PayBack create(PayBack payBack) {
        PayBackEntity entity =  new PayBackEntity();
        entity.setId(payBack.getId());
        entity.setName(payBack.getName());
        entity.setDescription(payBack.getDescription());
        entity.setAmount(payBack.getValor());
        if(payBack.getPos() != null){
            entity.setPos(posRepository.findById(payBack.getPos().getId()).orElseThrow(() -> new EntityNotFoundException("POS não encontrado!")));
        }
        if(payBack.getCustomer() != null){
            entity.setCustomer(customerRepository.findById(payBack.getCustomer().getId()).orElseThrow(() -> new EntityNotFoundException("Customer não encontrado!")));
        }
        return JpaFunctions.payBackEntityToPayBack.apply(payBackRepository.saveAndFlush(entity));
    }

    @Override
    public PayBack update(Long id, PayBack payBack) {
        PayBackEntity entity = payBackRepository.getById(id);
        entity.setId(payBack.getId());
        entity.setName(payBack.getName());
        entity.setDescription(payBack.getDescription());
        entity.setAmount(payBack.getValor());
        if(payBack.getPos() != null){
            entity.setPos(posRepository.findById(payBack.getPos().getId()).orElseThrow(() -> new EntityNotFoundException("POS não encontrado!")));
        }
        if(payBack.getCustomer() != null){
            entity.setCustomer(customerRepository.findById(payBack.getCustomer().getId()).orElseThrow(() -> new EntityNotFoundException("Customer não encontrado!")));
        }
        return JpaFunctions.payBackEntityToPayBack.apply(payBackRepository.saveAndFlush(entity));
    }

    @Override
    public void delete(Long id) {
        payBackRepository.deleteById(id);
    }

    @Override
    public List<PayBack> findAll() {
        return payBackRepository.findAll().stream().map(JpaFunctions.payBackEntityToPayBack).toList();
    }

    @Override
    public PayBack getById(Long id) {
        return JpaFunctions.payBackEntityToPayBack.apply(payBackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PayBack não encontrado!")));
    }
}
