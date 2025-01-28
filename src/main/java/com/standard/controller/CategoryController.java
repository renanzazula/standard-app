package com.standard.controller;

import com.standard.domain.Category;
import com.standard.service.category.CategoryService;
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
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/private/api/v1/category";

    private final CategoryService categoryService;

    @GetMapping({""})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_SEARCH')")
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_SEARCH')")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_ADD')")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_DELETE')")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_UPDATE')")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.update(id, category), HttpStatus.OK);
    }
}

