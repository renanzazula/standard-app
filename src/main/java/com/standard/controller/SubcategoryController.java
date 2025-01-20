package com.standard.controller;

import com.standard.domain.Category;
import com.standard.domain.Subcategory;
import com.standard.service.category.CategoryService;
import com.standard.service.subcategory.SubcategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(SubcategoryController.BASE_URL)
public class SubcategoryController {

    public static final String BASE_URL = "/private/api/v1/subcategory";

    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    @GetMapping({""})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_SEARCH')")
    public ResponseEntity<List<Subcategory>> findAll() {
        return new ResponseEntity<>(subcategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_SEARCH')")
    public ResponseEntity<Subcategory> findById(@PathVariable Long id) {
        return new ResponseEntity<>(subcategoryService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_SEARCH')")
    @GetMapping(value = "/category/{id}")
    public ResponseEntity<List<Subcategory>> findSubCategoryByCategory(@PathVariable Long id) {
        Category subCategoryList = categoryService.findById(id);
        return new ResponseEntity<>(subCategoryList.getSubcategories(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_ADD')")
    public ResponseEntity<Subcategory> create(@RequestBody Subcategory subcategory) {
        return new ResponseEntity<>(subcategoryService.create(subcategory), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_DELETE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        subcategoryService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_UPDATE')")
    @PutMapping({"/{id}"})
    public ResponseEntity<Subcategory> update(@PathVariable Long id, @RequestBody Subcategory subcategory) {
        return new ResponseEntity<>(subcategoryService.update(id, subcategory), HttpStatus.OK);
    }


}
