package com.standard.function.jpa;

import com.standard.domain.Category;
import com.standard.entity.CategoryEntity;
import com.standard.function.JpaFunctions;

public class CategoryToCategoryEntityFunction implements java.util.function.Function<CategoryEntity, Category> {

    @Override
    public Category apply(CategoryEntity input) {
        Category output = new Category();
        if (input != null) {
            output.setId(input.getId());
            output.setName(input.getName());
            output.setDescription(input.getDescription());
            output.setStatus(input.getStatus() != null ? input.getStatus().name() : "");
            if (input.getSubcategories() != null) {
                output.setSubcategories(input.getSubcategories().stream().map(JpaFunctions.subcategoryToSubCategoryEntity).toList());
            }
        }
        return output;
    }

}
