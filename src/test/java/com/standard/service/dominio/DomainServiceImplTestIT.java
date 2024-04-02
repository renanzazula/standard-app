package com.standard.service.dominio;

import com.standard.BaseTest;
import com.standard.domain.Domain;
import com.standard.repository.DomainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DomainServiceImplTestIT extends BaseTest {

    @Autowired
    private DomainRepository repository;

    private DomainService service;

    @BeforeEach
    public void setUp() {
        service = new DomainServiceImpl(repository);
        setUpDominio();
        domain = service.save(domain);
    }

    @Test
    public void incluir() {
        Domain saved = service.save(domain);
        assertNotNull(saved);

        Domain found = service.findById(saved.getCodigo());
        assertDominios(saved, found);
    }

    @Test
    public void alterar() {
        Domain update = service.findById(domain.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);
        update.setChecked(false);

        Domain updated = service.update(update.getCodigo(), update);
        assertDominios(update, updated);
    }

    @Test
    public void consultar() {
        List<Domain> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Domain found = service.findById(domain.getCodigo());
        assertNotNull(found);
        assertDominios(found, domain);
    }

    @Test
    public void excluir() {
        Domain delete = service.findById(domain.getCodigo());
        assertNotNull(delete);
        service.delete(delete.getCodigo());

        Domain found = service.findById(domain.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }
}
