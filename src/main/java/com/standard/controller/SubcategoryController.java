package com.standard.controller;

import com.standard.domain.Category;
import com.standard.domain.Subcategory;
import com.standard.service.category.CategoryService;
import com.standard.service.subcategory.SubcategoryService;
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
public class SubcategoryController implements SubcategoryControllerApi {

    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_SEARCH')")
    public ResponseEntity<List<Subcategory>> findAllSubcategories()
    {
        return new ResponseEntity<>(subcategoryService.findAll(), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_SEARCH')")
    public ResponseEntity<Subcategory> findSubcategoryById(@PathVariable Long id)
    {
        return new ResponseEntity<>(subcategoryService.findById(id), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_SEARCH')")
    public ResponseEntity<List<Subcategory>> findSubCategoryByCategory(@PathVariable Long id)
    {
        Category subCategoryList = categoryService.findById(id);
        return new ResponseEntity<>(subCategoryList.getSubcategories(), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_ADD')")
    public ResponseEntity<Subcategory> saveSubcategory(@RequestBody Subcategory subcategory)
    {
        return new ResponseEntity<>(subcategoryService.create(subcategory), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_DELETE')")
    public ResponseEntity<Void> deleteSubcategoryById(@PathVariable Long id)
    {
        subcategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('SUBCATEGORY_UPDATE')")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable Long id, @RequestBody Subcategory subcategory)
    {
        return new ResponseEntity<>(subcategoryService.update(id, subcategory), HttpStatus.OK);
    }

}
