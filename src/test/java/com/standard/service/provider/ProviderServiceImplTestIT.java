package com.standard.service.provider;

import com.standard.BaseTest;
import com.standard.domain.Provider;
import com.standard.repository.ProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
    public void incluir() {
        Provider saved = service.create(provider);
        assertNotNull(saved);

        Provider found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
    }

    @Test
    public void alterar() {
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
    public void consultar() {
        List<Provider> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Provider found = service.findById(provider.getId());
        assertNotNull(found);
        assertEquals(found.getId(), provider.getId());
    }

    @Test
    public void excluir() {

        Provider delete = service.findById(provider.getId());
        assertNotNull(delete);

        service.delete(delete.getId());

        Provider found = service.findById(provider.getId());
        assertNull(found.getId());
        assertNull(found.getName());
        assertNull(found.getDescription());
    }

}
