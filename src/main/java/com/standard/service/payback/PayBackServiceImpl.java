package com.standard.service.payback;

import com.standard.domain.PayBack;
import com.standard.entity.PayBackEntity;
import com.standard.function.JpaFunctions;
import com.standard.repository.CustomerRepository;
import com.standard.repository.PayBackRepository;
import com.standard.repository.PosRepository;
import com.standard.security.exceptions.CustomerNotFoundException;
import com.standard.security.exceptions.PayBackNotFoundException;
import com.standard.security.exceptions.PosNotFoundException;
import com.standard.util.ConstantMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
            entity.setPos(posRepository.findById(payBack.getPos().getId()).orElseThrow(() -> new PosNotFoundException(ConstantMessage.POS_NOT_FOUND)));
        }
        if(payBack.getCustomer() != null){
            entity.setCustomer(customerRepository.findById(payBack.getCustomer().getId()).orElseThrow(() -> new CustomerNotFoundException(ConstantMessage.CUSTOMER_NOT_FOUND)));
        }
        return JpaFunctions.payBackEntityToPayBack.apply(payBackRepository.saveAndFlush(entity));
    }

    @Override
    public PayBack update(Long id, PayBack payBack) {
        PayBackEntity entity = payBackRepository.findById(id).orElseThrow(() -> new PayBackNotFoundException(ConstantMessage.PAY_BACK_NOT_FOUND));
        entity.setId(payBack.getId());
        entity.setName(payBack.getName());
        entity.setDescription(payBack.getDescription());
        entity.setAmount(payBack.getValor());
        if(payBack.getPos() != null){
            entity.setPos(posRepository.findById(payBack.getPos().getId()).orElseThrow(() -> new PosNotFoundException(ConstantMessage.POS_NOT_FOUND)));
        }
        if(payBack.getCustomer() != null){
            entity.setCustomer(customerRepository.findById(payBack.getCustomer().getId()).orElseThrow(() -> new CustomerNotFoundException(ConstantMessage.CUSTOMER_NOT_FOUND)));
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
    public PayBack findById(Long id) {
        return JpaFunctions.payBackEntityToPayBack.apply(payBackRepository.findById(id).orElseThrow(() -> new PayBackNotFoundException(ConstantMessage.PAY_BACK_NOT_FOUND)));
    }
}
