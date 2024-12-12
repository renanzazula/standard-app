package com.standard.controller;

import com.standard.domain.Provider;
import com.standard.service.provider.ProviderService;
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
@RequestMapping(ProviderController.BASE_URL)
public class ProviderController {

    public static final String BASE_URL = "/private/api/v1/provider";

    private final ProviderService providerService;

    @GetMapping({""})
    @ApiOperation(value = "find all providers")
    public ResponseEntity<List<Provider>> findAll() {
        return new ResponseEntity<>(providerService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "find provider by id")
    public ResponseEntity<Provider> findById(@PathVariable Long id) {
        return new ResponseEntity<>(providerService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create a new provider")
    public ResponseEntity<Provider> create(@RequestBody Provider obj) {
        return new ResponseEntity<>(providerService.create(obj), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "delete provider by id")
    public void delete(@PathVariable Long id) {
        providerService.delete(id);
    }

    @PutMapping({"/{id}"})
    @ApiOperation(value = "update provider by id")
    public ResponseEntity<Provider> update(@PathVariable Long id, @RequestBody Provider obj) {
        return new ResponseEntity<>(providerService.update(id, obj), HttpStatus.OK);
    }

}
