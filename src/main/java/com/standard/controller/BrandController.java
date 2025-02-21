package com.standard.controller;

import com.standard.domain.Brand;
import com.standard.service.brand.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController()
public class BrandController implements BrandControllerApi {

    private final BrandService brandService;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('BRAND_SEARCH')")
    public ResponseEntity<List<Brand>> findAllBrands()
    {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('BRAND_SEARCH')")
    public ResponseEntity<Brand> findBrandById(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('BRAND_ADD')")
    public ResponseEntity<Brand> saveBrand(@RequestBody Brand obj)
    {
        return new ResponseEntity<>(brandService.create(obj), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('BRAND_DELETE')")
    public ResponseEntity<Void> deleteBrandById(@PathVariable Long id) {
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('BRAND_UPDATE')")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
        return new ResponseEntity<>(brandService.update(id, brand), HttpStatus.OK);
    }

}
