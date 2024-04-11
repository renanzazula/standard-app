package com.standard.service.provider;

import com.standard.BaseTest;
import com.standard.domain.Provider;
import com.standard.enums.StatusEnum;
import com.standard.repository.ProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Sql("/scripts/dataset.sql")
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
public class ProviderServiceImplTestIT extends BaseTest {

    @Autowired
    private ProviderRepository repository;

    private ProviderService service;

    @BeforeEach
    public void setUp() {
        service = new ProviderServiceImpl(repository);
        setUpFornecedor();
        provider = service.create(provider);
    }

    @Test
    public void create() {
        Provider saved = service.create(provider);
        assertNotNull(saved);

        Provider found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
    }

    @Test
    public void update() {
        Provider update = service.findById(provider.getId());
        assertNotNull(update);
        update.setName(NOME_UPDATE);
        update.setDescription(DESCRICAO_UPDATE);

        Provider updated = service.update(update.getId(), update);
        assertEquals(update.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getDescription(), updated.getDescription());
    }

    @Test
    public void findAll() {
        List<Provider> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void findById() {
        Provider found = service.findById(provider.getId());
        assertNotNull(found);
        assertEquals(found.getId(), provider.getId());
    }

    @Test
    public void delete() {
        Provider delete = service.findById(provider.getId());
        assertNotNull(delete);

        service.delete(delete.getId());
        Provider found = service.findById(provider.getId());
        assertEquals(found.getStatus(), StatusEnum.INATIVO.name());
    }

}
