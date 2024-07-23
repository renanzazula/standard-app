package com.standard.service.paymentmethod;

import com.standard.domain.PaymentMethod;
import com.standard.entity.PaymentMethodEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.PaymentMethodRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    @Transactional
    public PaymentMethod create(PaymentMethod objct) {
        PaymentMethodEntity paymentMethodDB = new PaymentMethodEntity();
        return getPaymentMethod(objct, paymentMethodDB);
    }

    @Override
    @Transactional
    public PaymentMethod update(Long id, PaymentMethod objct) {
        PaymentMethodEntity paymentMethodDB = paymentMethodRepository.getOne(id);
        return getPaymentMethod(objct, paymentMethodDB);
    }

    private PaymentMethod getPaymentMethod(PaymentMethod objct, PaymentMethodEntity paymentMethodDB) {
        paymentMethodDB.setName(objct.getName());
        paymentMethodDB.setDescription(objct.getDescription());
        paymentMethodDB.setDiscountPercent(objct.getDiscountPercent());
        paymentMethodRepository.saveAndFlush(paymentMethodDB);
        return JpaFunctions.paymentMethodToPaymentMethodEntity.apply(paymentMethodRepository.saveAndFlush(paymentMethodDB));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PaymentMethodEntity paymentMethodDB = paymentMethodRepository.getOne(id);
        if(paymentMethodDB != null) {
            paymentMethodDB.setStatus(StatusEnum.DISABLE);
        }
        paymentMethodRepository.save(paymentMethodDB);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "paymentMethodListCache", condition = "#showInventoryOnHand == false")
    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll().stream().map(JpaFunctions.paymentMethodToPaymentMethodEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "paymentMethodCache", key = "#id", condition = "#showInventoryOnHand == false")
    public PaymentMethod findById(Long id) {
        return JpaFunctions.paymentMethodToPaymentMethodEntity
                .apply(paymentMethodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado!")));
    }

}
