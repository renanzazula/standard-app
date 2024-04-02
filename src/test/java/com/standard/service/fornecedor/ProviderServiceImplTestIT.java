package com.standard.service.fornecedor;

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
        provider = service.save(provider);
    }

    @Test
    public void incluir() {
        Provider saved = service.save(provider);
        assertNotNull(saved);

        Provider found = service.findById(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Provider update = service.findById(provider.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Provider updated = service.update(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Provider> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Provider found = service.findById(provider.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), provider.getCodigo());
    }

    @Test
    public void excluir() {

        Provider delete = service.findById(provider.getCodigo());
        assertNotNull(delete);

        service.delete(delete.getCodigo());

        Provider found = service.findById(provider.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }

}
