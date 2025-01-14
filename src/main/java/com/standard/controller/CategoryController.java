package com.standard.controller;

import com.standard.domain.Category;
import com.standard.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/private/api/v1/category";

    private final CategoryService categoryService;

    @GetMapping({""})
    @PreAuthorize("hasAuthority('CATEGORY_SEARCH')")
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasAuthority('CATEGORY_SEARCH')")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CATEGORY_ADD')")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('CATEGORY_DELETE')")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.update(id, category), HttpStatus.OK);
    }
}

