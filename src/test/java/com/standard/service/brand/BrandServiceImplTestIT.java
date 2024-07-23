package com.standard.service.brand;

import com.standard.BaseTest;
import com.standard.domain.Brand;
import com.standard.enums.StatusEnum;
import com.standard.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@Sql("/scripts/dataset.sql")
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class BrandServiceImplTestIT extends BaseTest {

    @Autowired
    private BrandRepository repository;

    private BrandService service;

    @BeforeEach
    void setUp() {
        service = new BrandServiceImpl(repository);
        setUpBrand();
        brand = service.create(brand);
    }

    @Test
    void create() {
        Brand saved = service.create(brand);
        assertNotNull(saved);

        Brand found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
    }

    @Test
    void update() {
        Brand update = service.findById(brand.getId());
        assertNotNull(update);
        update.setName(NAME_UPDATE);
        update.setDescription(DESCRIPTION_UPDATE);

        Brand updated = service.update(update.getId(), update);
        assertEquals(update.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getDescription(), updated.getDescription());
    }

    @Test
    void findAll() {
        List<Brand> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    void findById() {
        Brand found = service.findById(brand.getId());
        assertNotNull(found);
        assertEquals(found.getId(), brand.getId());
    }

    @Test
    void delete() {

        Brand delete = service.findById(brand.getId());
        assertNotNull(delete);

        service.delete(delete.getId());

        Brand found = service.findById(brand.getId());
        assertEquals(found.getStatus(), StatusEnum.DISABLE);
    }
}
