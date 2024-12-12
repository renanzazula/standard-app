package com.standard.controller;

import com.standard.domain.PaymentMethod;
import com.standard.service.paymentmethod.PaymentMethodService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(PaymentMethodController.BASE_URL)
public class PaymentMethodController {

    public static final String BASE_URL = "/private/api/v1/paymentMethod";

    private final PaymentMethodService paymentMethodService;

    @GetMapping({""})
    @ApiOperation(value = "find all payment methods")
    public ResponseEntity<List<PaymentMethod>> findAll() {
        return new ResponseEntity<>(paymentMethodService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "find a payment method by id")
    public ResponseEntity<PaymentMethod> findById(@PathVariable Long id) {
        return new ResponseEntity<>(paymentMethodService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create payment methods")
    public ResponseEntity<PaymentMethod> create(@RequestBody PaymentMethod obj) {
        return new ResponseEntity<>(paymentMethodService.create(obj), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "delete payment method by id")
    public void delete(@PathVariable Long id) {
        paymentMethodService.delete(id);
    }

    @PutMapping({"/{id}"})
    @ApiOperation(value = "update payment method by id")
    public ResponseEntity<PaymentMethod> update(@PathVariable Long id, @RequestBody PaymentMethod obj) {
        return new ResponseEntity<>(paymentMethodService.update(id, obj), HttpStatus.OK);
    }
}
