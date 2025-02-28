package com.standard.service.provider;

import com.standard.BaseTest;
import com.standard.domain.Provider;
import com.standard.enums.StatusEnum;
import com.standard.repository.ProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class ProviderServiceImplTestIT extends BaseTest {

    @Autowired
    private ProviderRepository repository;

    private ProviderService service;

    @BeforeEach
    void setUp() {
        service = new ProviderServiceImpl(repository);
        setUpProvider();
        provider = service.create(provider);
    }

    @Test
    void create() {
        Provider saved = service.create(provider);
        assertNotNull(saved);

        Provider found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
    }

    @Test
    void update() {
        Provider update = service.findById(provider.getId());
        assertNotNull(update);
        update.setName(NAME_UPDATE);
        update.setDescription(DESCRIPTION_UPDATE);

        Provider updated = service.update(update.getId(), update);
        assertEquals(update.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getDescription(), updated.getDescription());
    }

    @Test
    void findAll() {
        List<Provider> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    void findById() {
        Provider found = service.findById(provider.getId());
        assertNotNull(found);
        assertEquals(found.getId(), provider.getId());
    }

    @Test
    void delete() {
        Provider delete = service.findById(provider.getId());
        assertNotNull(delete);

        service.delete(delete.getId());
        Provider found = service.findById(provider.getId());
        assertEquals(found.getStatus(), StatusEnum.DISABLE.name());
    }

}
