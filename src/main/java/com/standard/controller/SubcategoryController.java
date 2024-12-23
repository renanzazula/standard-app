package com.standard.controller;

import com.standard.domain.Category;
import com.standard.domain.Subcategory;
import com.standard.service.category.CategoryService;
import com.standard.service.subcategory.SubcategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(SubcategoryController.BASE_URL)
public class SubcategoryController {

    public static final String BASE_URL = "/private/api/v1/subcategory";

    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    @GetMapping({""})
    public ResponseEntity<List<Subcategory>> findAll() {
        return new ResponseEntity<>(subcategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Subcategory> findById(@PathVariable Long id) {
        return new ResponseEntity<>(subcategoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subcategory> create(@RequestBody Subcategory subcategory) {
        return new ResponseEntity<>(subcategoryService.create(subcategory), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        subcategoryService.delete(id);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Subcategory> update(@PathVariable Long id, @RequestBody Subcategory subcategory) {
        return new ResponseEntity<>(subcategoryService.update(id, subcategory), HttpStatus.OK);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<List<Subcategory>> findSubCategoryByCategory(@PathVariable Long id) {
        Category subCategoryList = categoryService.findById(id);
        return new ResponseEntity<>(subCategoryList.getSubcategories(), HttpStatus.OK);
    }
}
