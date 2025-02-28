package com.standard.controller;

import com.standard.domain.Category;
import com.standard.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController  implements CategoryControllerApi {

    private final CategoryService categoryService;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_SEARCH')")
    public ResponseEntity<List<Category>> findAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_SEARCH')")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_ADD')")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_DELETE')")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('CATEGORY_UPDATE')")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.update(id, category), HttpStatus.OK);
    }
}

