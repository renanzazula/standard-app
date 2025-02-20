package com.standard.service.measure;

import com.standard.BaseTest;
import com.standard.domain.Measure;
import com.standard.domain.Product;
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

	private MeasureService measureService;

	@BeforeEach
    void setUp() {
        measureService = new MeasureServiceImpl(brandRepository, measureRepository, categoryRepository, subcategoryRepository);

		BrandService brandService = new BrandServiceImpl(brandRepository);
		SubcategoryService subcategoryService = new SubcategoryServiceImpl(subcategoryRepository);
		CategoryService categoryService = new CategoryServiceImpl(categoryRepository, subcategoryRepository);

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
        measure.setItemsTypeMeasure(itemsTypeMeasureList);

    }

    @Test
    void create() {
        Measure measureSave = measureService.create(measure);
        assertEquals(measureSave.getItemsTypeMeasure().size(), measure.getItemsTypeMeasure().size());

        measure = measureService.findById(measureSave.getId());
        assertBrandSubCategoryCategoryAmount(measureSave);

    }

    @Test
    void update() {
        measure.setSubcategory(subcategory);
        measure.setCategory(category);
        measure.setBrand(brand);
        measure.setItemsTypeMeasure(itemsTypeMeasureList);
        measure = measureService.create(measure);

        Measure toUpdate = measureService.findById(measure.getId());
        toUpdate.setDescription(measure.getDescription() + "_updated");

        Measure updated = measureService.update(measure.getId(), toUpdate);
        assertEquals(updated.getDescription(), toUpdate.getDescription());
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
        measure = measureService.create(measure);

        product = new Product();
        product.setBrand(brand);
        product.setCategory(category);
        product.setSubcategory(subcategory);
        product.setMeasure(measure);

        List<Measure> listOfMeasures = measureService.findByCategorySubcategoryBrand(product);
        assertNotNull(listOfMeasures);

    }
}
