package com.standard.service.payback;

import com.standard.domain.PayBack;
import com.standard.entity.PayBackEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.PosRepository;
import com.standard.repository.CustomerRepository;
import com.standard.repository.PayBackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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
            entity.setPos(posRepository.getOne(payBack.getPos().getId()));
        }
        if(payBack.getCustomer() != null){
            entity.setCustomer(customerRepository.getOne(payBack.getCustomer().getId()));
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
            entity.setPos(posRepository.getOne(payBack.getPos().getId()));
        }
        if(payBack.getCustomer() != null){
            entity.setCustomer(customerRepository.getOne(payBack.getCustomer().getId()));
        }
        return JpaFunctions.payBackEntityToPayBack.apply(payBackRepository.saveAndFlush(entity));
    }

    @Override
    public void delete(Long id) {
        payBackRepository.deleteById(id);
    }

    @Override
    public List<PayBack> findAll() {
        return payBackRepository.findAll().stream().map(JpaFunctions.payBackEntityToPayBack).collect(Collectors.toList());
    }

    @Override
    public PayBack getById(Long id) {
        return JpaFunctions.payBackEntityToPayBack.apply(payBackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
    }
}
