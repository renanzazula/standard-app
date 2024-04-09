package com.standard.controller;

import com.standard.domain.Provider;
import com.standard.service.provider.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ProviderController.BASE_URL)
public class ProviderController {

    public static final String BASE_URL = "/private/v1/provider";

    private final ProviderService providerService;

    @GetMapping({""})
    public ResponseEntity<List<Provider>> findAll() {
        return new ResponseEntity<>(providerService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Provider> findById(@PathVariable Long id) {
        return new ResponseEntity<>(providerService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Provider> save(@RequestBody Provider obj) {
        return new ResponseEntity<>(providerService.create(obj), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        providerService.delete(id);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Provider> update(@PathVariable Long id, @RequestBody Provider obj) {
        return new ResponseEntity<>(providerService.update(id, obj), HttpStatus.OK);
    }

}
