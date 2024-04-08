package com.standard.function.jpa;

import com.standard.domain.Category;
import com.standard.entity.CategoryEntity;
import com.standard.function.JpaFunctions;

import java.util.stream.Collectors;

public class CategoryToCategoryEntityFunction implements java.util.function.Function<CategoryEntity, Category> {

    @Override
    public Category apply(CategoryEntity input) {
        Category output = new Category();
        if (input != null) {
            output.setId(input.getId());
            output.setName(input.getName());
            output.setDescription(input.getDescription());
            if (input.getSubcategories() != null) {
                output.setSubcategories(input.getSubcategories().stream().map(JpaFunctions.subcategoryToSubCategoryEntity).collect(Collectors.toList()));
            }
        }
        return output;
    }

}
