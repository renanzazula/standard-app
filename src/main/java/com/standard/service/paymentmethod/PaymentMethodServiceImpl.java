package com.standard.service.paymentmethod;

import com.standard.domain.PaymentMethod;
import com.standard.entity.PaymentMethodEntity;
import com.standard.enums.StatusEnum;
import com.standard.function.JpaFunctions;
import com.standard.repository.PaymentMethodRepository;
import com.standard.security.exceptions.PaymentMethodNotFoundException;
import com.standard.util.ConstantMessage;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    @Transactional
    public PaymentMethod create(PaymentMethod paymentMethod) {
        PaymentMethodEntity paymentMethodDB = new PaymentMethodEntity();
        return getPaymentMethod(paymentMethod, paymentMethodDB);
    }

    @Override
    @Transactional
    public PaymentMethod update(Long id, PaymentMethod paymentMethod) {
        PaymentMethodEntity paymentMethodDB = paymentMethodRepository.findById(id).orElseThrow(() -> new PaymentMethodNotFoundException(ConstantMessage.PAYMENT_METHOD_NOT_FOUND));
        return getPaymentMethod(paymentMethod, paymentMethodDB);
    }

    private PaymentMethod getPaymentMethod(PaymentMethod object, PaymentMethodEntity paymentMethodDB) {
        paymentMethodDB.setName(object.getName());
        paymentMethodDB.setDescription(object.getDescription());
        paymentMethodDB.setDiscountPercent(object.getDiscountPercent());
        paymentMethodRepository.saveAndFlush(paymentMethodDB);
        return JpaFunctions.paymentMethodToPaymentMethodEntity.apply(paymentMethodRepository.saveAndFlush(paymentMethodDB));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PaymentMethodEntity paymentMethodDB = paymentMethodRepository.findById(id).orElseThrow(() -> new PaymentMethodNotFoundException(ConstantMessage.PAYMENT_METHOD_NOT_FOUND));
        paymentMethodDB.setStatus(StatusEnum.DISABLE);
        paymentMethodRepository.save(paymentMethodDB);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "paymentMethodListCache")
    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll().stream().map(JpaFunctions.paymentMethodToPaymentMethodEntity).toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "paymentMethodCache", key = "#id")
    public PaymentMethod findById(Long id) {
        return JpaFunctions.paymentMethodToPaymentMethodEntity
                .apply(paymentMethodRepository.findById(id).orElseThrow(() -> new PaymentMethodNotFoundException(ConstantMessage.PAYMENT_METHOD_NOT_FOUND)));
    }

}
