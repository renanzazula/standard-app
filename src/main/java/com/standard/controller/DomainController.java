package com.standard.controller;


import com.standard.domain.Domain;
import com.standard.service.domain.DomainService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(DomainController.BASE_URL)
public class DomainController {

    public static final String BASE_URL = "/private/v1/domain";

    private final DomainService domainService;

    @GetMapping({""})
    @ApiOperation(value = "find all the domains")
    public ResponseEntity<List<Domain>> findAll() {
        return new ResponseEntity<>(domainService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "find domains by id")
    public ResponseEntity<Domain> findById(@PathVariable Long id) {
        return new ResponseEntity<>(domainService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create a domain")
    public ResponseEntity<Domain> create(@RequestBody Domain domain) {
        return new ResponseEntity<>(domainService.create(domain), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "delete domains by id")
    public void delete(@PathVariable Long id) {
        domainService.delete(id);
    }

    @PutMapping({"/{id}"})
    @ApiOperation(value = "update domains by id")
    public ResponseEntity<Domain> update(@PathVariable Long id, @RequestBody Domain domain) {
        return new ResponseEntity<>(domainService.update(id, domain), HttpStatus.OK);
    }
}
