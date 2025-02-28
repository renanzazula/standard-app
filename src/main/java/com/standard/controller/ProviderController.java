package com.standard.controller;

import com.standard.domain.Provider;
import com.standard.service.provider.ProviderService;
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
public class ProviderController implements ProviderControllerApi {

    private final ProviderService providerService;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_SEARCH')")
    public ResponseEntity<List<Provider>> findAllProviders()
    {
        return new ResponseEntity<>(providerService.findAll(), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_SEARCH')")
    public ResponseEntity<Provider> findProviderById(@PathVariable Long id)
    {
        return new ResponseEntity<>(providerService.findById(id), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_ADD')")
    public ResponseEntity<Provider> saveProvider(@RequestBody Provider obj)
    {
        return new ResponseEntity<>(providerService.create(obj), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_DELETE')")
    public ResponseEntity<Void  > deleteProviderById(@PathVariable Long id)
    {
        providerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('PROVIDER_UPDATE')")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id, @RequestBody Provider obj)
    {
        return new ResponseEntity<>(providerService.update(id, obj), HttpStatus.OK);
    }

}
