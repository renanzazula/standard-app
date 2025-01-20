package com.standard.controller;

import com.standard.domain.Domain;
import com.standard.service.domain.DomainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(DomainController.BASE_URL)
public class DomainController {

    public static final String BASE_URL = "/private/api/v1/domain";

    private final DomainService domainService;

    @GetMapping({""})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_SEARCH')")
    public ResponseEntity<List<Domain>> findAll() {
        return new ResponseEntity<>(domainService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_SEARCH')")
    public ResponseEntity<Domain> findById(@PathVariable Long id) {
        return new ResponseEntity<>(domainService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_ADD')")
    public ResponseEntity<Domain> create(@RequestBody Domain domain) {
        return new ResponseEntity<>(domainService.create(domain), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_DELETE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        domainService.delete(id);
    }

    @PutMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_UPDATE')")
    public ResponseEntity<Domain> update(@PathVariable Long id, @RequestBody Domain domain) {
        return new ResponseEntity<>(domainService.update(id, domain), HttpStatus.OK);
    }
}
