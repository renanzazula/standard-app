package com.standard.service.category;

import com.standard.BaseTest;
import com.standard.domain.Category;
import com.standard.domain.Subcategory;
import com.standard.enums.StatusEnum;
import com.standard.repository.CategoryRepository;
import com.standard.repository.SubcategoryRepository;
import com.standard.service.subcategory.SubcategoryService;
import com.standard.service.subcategory.SubcategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Sql("/scripts/dataset.sql")
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class CategoryServiceImplTestIT extends BaseTest {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    private CategoryService service;

    private Category obj = null;

    @BeforeEach
    void setUp() {
        service = new CategoryServiceImpl(repository, subcategoryRepository);
        SubcategoryService subcategoryService = new SubcategoryServiceImpl(subcategoryRepository);

        List<Subcategory> subcategories = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Subcategory subcategory = new Subcategory();
            subcategory.setName(NOME + "_" + i);
            subcategory.setDescription(DESCRICAO + "_" + i);
            subcategories.add(subcategoryService.create(subcategory));
        }

        obj = new Category();
        obj.setName(NOME);
        obj.setDescription(DESCRICAO);
        obj.setSubcategories(subcategories);
        obj = service.create(obj);
    }

    @Test
    void create() {
        Category saved = service.create(obj);
        assertNotNull(saved);

        Category found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
    }

    @Test
    void update() {
        Category update = service.findById(obj.getId());
        assertNotNull(update);
        update.setName(NOME_UPDATE);
        update.setDescription(DESCRICAO_UPDATE);

        Category updated = service.update(update.getId(), update);
        assertEquals(update.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getDescription(), updated.getDescription());
    }

    @Test
    void findAll() {
        List<Category> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    void findById() {
        Category found = service.findById(obj.getId());
        assertNotNull(found);
        assertEquals(found.getId(), obj.getId());
    }

    @Test
    void delete() {
        List<Category> foundList = service.findAll();
        assertNotNull(foundList);

        for (Category category : foundList) {
            Category delete = service.findById(category.getId());
            assertNotNull(delete);
            service.delete(delete.getId());

            Category found = service.findById(delete.getId());
            assertEquals(found.getStatus(), StatusEnum.INATIVO.name());
        }
    }
}
