package com.standard.controller;

import com.standard.domain.PaymentMethod;
import com.standard.service.paymentmethod.PaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(PaymentMethodController.BASE_URL)
public class PaymentMethodController {

    public static final String BASE_URL = "/private/api/v1/paymentMethod";

    private final PaymentMethodService paymentMethodService;

    @GetMapping({""})
    public ResponseEntity<List<PaymentMethod>> findAll() {
        return new ResponseEntity<>(paymentMethodService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<PaymentMethod> findById(@PathVariable Long id) {
        return new ResponseEntity<>(paymentMethodService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> create(@RequestBody PaymentMethod obj) {
        return new ResponseEntity<>(paymentMethodService.create(obj), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paymentMethodService.delete(id);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<PaymentMethod> update(@PathVariable Long id, @RequestBody PaymentMethod obj) {
        return new ResponseEntity<>(paymentMethodService.update(id, obj), HttpStatus.OK);
    }
}
