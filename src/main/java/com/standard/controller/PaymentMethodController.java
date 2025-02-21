package com.standard.controller;

import com.standard.domain.PaymentMethod;
import com.standard.service.paymentmethod.PaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentMethodController implements PaymentMethodControllerApi {

    private final PaymentMethodService paymentMethodService;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PAYMENT_METHOD_SEARCH')")
    public ResponseEntity<List<PaymentMethod>> findAllPaymentMethods()
    {
        return new ResponseEntity<>(paymentMethodService.findAll(), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PAYMENT_METHOD_SEARCH')")
    public ResponseEntity<PaymentMethod> findPaymentMethodById(@PathVariable Long id) {
        return new ResponseEntity<>(paymentMethodService.findById(id), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PAYMENT_METHOD_ADD')")
    public ResponseEntity<PaymentMethod> savePaymentMethod(@RequestBody PaymentMethod obj) {
        return new ResponseEntity<>(paymentMethodService.create(obj), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PAYMENT_METHOD_DELETE')")
    public ResponseEntity<Void> deletePaymentMethodById(@PathVariable Long id) {
        paymentMethodService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PAYMENT_METHOD_UPDATE')")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethod obj) {
        return new ResponseEntity<>(paymentMethodService.update(id, obj), HttpStatus.OK);
    }
}
