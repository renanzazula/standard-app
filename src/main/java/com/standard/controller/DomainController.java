package com.standard.controller;

import com.standard.domain.Domain;
import com.standard.service.domain.DomainService;
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
public class DomainController implements DomainControllerApi {

    private final DomainService domainService;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_SEARCH')")
    public ResponseEntity<List<Domain>> findAllDomains() {
        return new ResponseEntity<>(domainService.findAll(), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_SEARCH')")
    public ResponseEntity<Domain> findDomainById(@PathVariable Long id) {
        return new ResponseEntity<>(domainService.findById(id), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_ADD')")
    public ResponseEntity<Domain> saveDomain(@RequestBody Domain domain) {
        return new ResponseEntity<>(domainService.create(domain), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_DELETE')")
    public ResponseEntity<Void> deleteDomainById(@PathVariable Long id) {
        domainService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DOMAIN_UPDATE')")
    public ResponseEntity<Domain> updateDomain(@PathVariable Long id, @RequestBody Domain domain) {
        return new ResponseEntity<>(domainService.update(id, domain), HttpStatus.OK);
    }
}
