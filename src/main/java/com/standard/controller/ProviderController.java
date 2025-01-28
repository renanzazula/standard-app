package com.standard.controller;

import com.standard.domain.Provider;
import com.standard.service.provider.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(ProviderController.BASE_URL)
public class ProviderController {

    public static final String BASE_URL = "/private/api/v1/provider";

    private final ProviderService providerService;

    @GetMapping({""})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_SEARCH')")
    public ResponseEntity<List<Provider>> findAll() {
        return new ResponseEntity<>(providerService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_SEARCH')")
    public ResponseEntity<Provider> findById(@PathVariable Long id) {
        return new ResponseEntity<>(providerService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_ADD')")
    public ResponseEntity<Provider> create(@RequestBody Provider obj) {
        return new ResponseEntity<>(providerService.create(obj), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_DELETE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        providerService.delete(id);
    }

    @PutMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_UPDATE')")
    public ResponseEntity<Provider> update(@PathVariable Long id, @RequestBody Provider obj) {
        return new ResponseEntity<>(providerService.update(id, obj), HttpStatus.OK);
    }

}
