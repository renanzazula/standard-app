package com.standard.service.domain;

import com.standard.BaseTest;
import com.standard.domain.Domain;
import com.standard.repository.DomainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class DomainServiceImplTestIT extends BaseTest {

    @Autowired
    private DomainRepository repository;

    private DomainService service;

    @BeforeEach
    void setUp() {
        service = new DomainServiceImpl(repository);
        setUpDomain();
        domain = service.create(domain);
    }

    @Test
    void create() {
        Domain saved = service.create(domain);
        assertNotNull(saved);

        Domain found = service.findById(saved.getId());
        assertDomain(saved, found);
    }

    @Test
    void update() {
        Domain update = service.findById(domain.getId());
        assertNotNull(update);
        update.setName(NAME_UPDATE);
        update.setDescription(DESCRIPTION_UPDATE);
        update.setChecked(false);

        Domain updated = service.update(update.getId(), update);
        assertDomain(update, updated);
    }

    @Test
    void findAll() {
        List<Domain> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    void findById() {
        Domain found = service.findById(domain.getId());
        assertNotNull(found);
        assertDomain(found, domain);
    }

    @Test
    void delete() {
        Domain delete = service.findById(domain.getId());
        assertNotNull(delete);
        service.delete(delete.getId());

        Domain found = service.findById(domain.getId());

    }
}
