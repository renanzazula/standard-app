package com.standard.service.measure;

import com.standard.BaseTest;
import com.standard.domain.Brand;
import com.standard.domain.Category;
import com.standard.domain.Measure;
import com.standard.domain.Subcategory;
import com.standard.enums.StatusEnum;
import com.standard.repository.BrandRepository;
import com.standard.repository.CategoryRepository;
import com.standard.repository.MeasureRepository;
import com.standard.repository.SubcategoryRepository;
import com.standard.service.brand.BrandService;
import com.standard.service.brand.BrandServiceImpl;
import com.standard.service.category.CategoryService;
import com.standard.service.category.CategoryServiceImpl;
import com.standard.service.subcategory.SubcategoryService;
import com.standard.service.subcategory.SubcategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class MeasureServiceImplTestIT extends BaseTest {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    private BrandService brandService;
    private MeasureService measureService;
    private CategoryService categoryService;
    private SubcategoryService subcategoryService;

    @BeforeEach
    void setUp() {
        measureService = new MeasureServiceImpl(brandRepository, measureRepository, categoryRepository,
                subcategoryRepository);

        brandService = new BrandServiceImpl(brandRepository);
        subcategoryService = new SubcategoryServiceImpl(subcategoryRepository);
        categoryService = new CategoryServiceImpl(categoryRepository, subcategoryRepository);

        setUpBrand();
        brand = brandService.create(brand);

        setUpSubcategory();
        subcategory = subcategoryService.create(subcategory);

        setUpCategory();
        category.setSubcategories(new ArrayList<>());
        category.getSubcategories().add(subcategory);
        category = categoryService.create(category);


        setUpItemsTypeMeasure();
        setUpMeasure();
        measure.setSubcategory(subcategory);
        measure.setCategory(category);
        measure.setBrand(brand);
        measure.setItemsTypeMeasure(itemsTypeMeasure);

    }

    @Test
    void create() {
        Measure measureSave = measureService.create(measure);
        assertEquals(measureSave.getItemsTypeMeasure().size(), measure.getItemsTypeMeasure().size());

        measure = measureService.findById(measureSave.getId());
        assertBrandSubCategoryCategoryAmount(measureSave);

    }

    @Test
    void alterar() {
        // todo:
    }

    @Test
    void updateBrand() {

        measure = measureService.create(measure);

        Brand brandUpdate = new Brand();
        brandUpdate.setName(NAME_UPDATE);
        brandUpdate.setDescription(DESCRIPTION_UPDATE);
        brandUpdate = brandService.create(brandUpdate);

        Measure toUpdate = measureService.findById(measure.getId());
        toUpdate.setCategory(category);
        toUpdate.setSubcategory(subcategory);
        toUpdate.setBrand(brandUpdate);

        Measure updated = measureService.update(measure.getId(), toUpdate);

        for (int i = 0; i < updated.getItemsTypeMeasure().size(); i++) {
            Brand brandFound = updated.getItemsTypeMeasure().get(i).getBrand();
            assertBrand(brandFound, brandUpdate);

            assertNotEquals(brandUpdate.getId(), brand.getId());
            assertNotEquals(brandUpdate.getName(), brand.getName());
            assertNotEquals(brandUpdate.getDescription(), brand.getDescription());
        }

    }

    @Test
    void updateSubCategory() {

        measure = measureService.create(measure);

        Subcategory subcategoryUpdate = new Subcategory();
        subcategoryUpdate.setName(NAME_UPDATE);
        subcategoryUpdate.setDescription(DESCRIPTION_UPDATE);
        subcategoryUpdate = subcategoryService.create(subcategoryUpdate);

        Measure toUpdate = measureService.findById(measure.getId());
        toUpdate.setCategory(category);
        toUpdate.setSubcategory(subcategoryUpdate);
        toUpdate.setBrand(brand);

        Measure updated = measureService.update(measure.getId(), toUpdate);

        for (int i = 0; i < updated.getItemsTypeMeasure().size(); i++) {
            Subcategory subcategoryFound = updated.getItemsTypeMeasure().get(i).getSubcategory();
            assertSubcategory(subcategoryFound, subcategoryUpdate);

            assertNotEquals(subcategoryUpdate.getId(), subcategory.getId());
            assertNotEquals(subcategoryUpdate.getName(), subcategory.getName());
            assertNotEquals(subcategoryUpdate.getDescription(), subcategory.getDescription());
        }

    }

    @Test
    void updateCategory() {
        measure = measureService.create(measure);

        Category categoryUpdate = new Category();
        categoryUpdate.setName(NAME_UPDATE);
        categoryUpdate.setDescription(DESCRIPTION_UPDATE);
        categoryUpdate.setSubcategories(new ArrayList<>());
        categoryUpdate.getSubcategories().add(subcategory);
        categoryUpdate = categoryService.create(categoryUpdate);

        Measure toUpdate = measureService.findById(measure.getId());
        toUpdate.setCategory(categoryUpdate);
        toUpdate.setSubcategory(subcategory);
        toUpdate.setBrand(brand);

        Measure updated = measureService.update(measure.getId(), toUpdate);
        for (int i = 0; i < updated.getItemsTypeMeasure().size(); i++) {
            Category categoryFound = updated.getItemsTypeMeasure().get(i).getCategory();
            assertCategory(categoryFound, categoryUpdate);

            assertNotEquals(categoryFound.getId(), category.getId());
            assertNotEquals(categoryFound.getName(), category.getName());
            assertNotEquals(categoryFound.getDescription(), category.getDescription());
        }
    }

    @Test
    void delete() {
        measure = measureService.create(measure);
        Measure delete = measureService.findById(measure.getId());
        assertNotNull(delete);

        measureService.delete(delete.getId());
        Measure found = measureService.findById(measure.getId());
        assertEquals(found.getStatus(), StatusEnum.DISABLE.name());
    }

    @Test
    void findAll() {
        List<Measure> measures = measureService.findAll();
        assertNotNull(measures);
    }

    @Test
    void findById() {
        measure = measureService.create(measure);
        Measure measureFound = measureService.findById(measure.getId());
        assertEquals(measureFound.getId(), measure.getId());
        assertEquals(measureFound.getNome(), measure.getNome());
        assertEquals(measureFound.getDescription(), measure.getDescription());
        assertBrandSubCategoryCategoryAmount(measureFound);
    }

    @Test
    void findByCategorySubcategoryAndBrand() {
        // TODO
    }
}
