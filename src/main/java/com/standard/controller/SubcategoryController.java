package com.standard.controller;

import com.standard.domain.Category;
import com.standard.domain.Subcategory;
import com.standard.service.category.CategoryService;
import com.standard.service.subcategory.SubcategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(SubcategoryController.BASE_URL)
public class SubcategoryController {

    public static final String BASE_URL = "/private/v1/subcategory";

    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    @GetMapping({""})
    @ApiOperation(value = "find all subcategories")
    public ResponseEntity<List<Subcategory>> findAll() {
        return new ResponseEntity<>(subcategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "find subcategories by id")
    public ResponseEntity<Subcategory> findById(@PathVariable Long id) {
        return new ResponseEntity<>(subcategoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create new subcategory")
    public ResponseEntity<Subcategory> create(@RequestBody Subcategory subcategory) {
        return new ResponseEntity<>(subcategoryService.create(subcategory), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "delete subcategory by id")
    public void delete(@PathVariable Long id) {
        subcategoryService.delete(id);
    }

    @PutMapping({"/{id}"})
    @ApiOperation(value = "update subcategory by id")
    public ResponseEntity<Subcategory> update(@PathVariable Long id, @RequestBody Subcategory subcategory) {
        return new ResponseEntity<>(subcategoryService.update(id, subcategory), HttpStatus.OK);
    }

    @GetMapping(value = "/category/{id}")
    @ApiOperation(value = "find subcategory by category id")
    public ResponseEntity<List<Subcategory>> findSubCategoryByCategory(@PathVariable Long id) {
        Category subCategoryList = categoryService.findById(id);
        return new ResponseEntity<>(subCategoryList.getSubcategories(), HttpStatus.OK);
    }
}
