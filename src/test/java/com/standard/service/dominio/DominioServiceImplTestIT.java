package com.standard.service.dominio;

import com.standard.BaseTest;
import com.standard.domain.Dominio;
import com.standard.repository.DominioRepository;
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
public class DominioServiceImplTestIT extends BaseTest {

    @Autowired
    private DominioRepository repository;

    private DominioService service;

    @BeforeEach
    public void setUp() {
        service = new DominioServiceImpl(repository);
        setUpDominio();
        dominio = service.incluir(dominio);
    }

    @Test
    public void incluir() {
        Dominio saved = service.incluir(dominio);
        assertNotNull(saved);

        Dominio found = service.consultarByCodigo(saved.getCodigo());
        assertDominios(saved, found);
    }

    @Test
    public void alterar() {
        Dominio update = service.consultarByCodigo(dominio.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);
        update.setChecked(false);

        Dominio updated = service.alterar(update.getCodigo(), update);
        assertDominios(update, updated);
    }

    @Test
    public void consultar() {
        List<Dominio> found = service.consultar();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Dominio found = service.consultarByCodigo(dominio.getCodigo());
        assertNotNull(found);
        assertDominios(found, dominio);
    }

    @Test
    public void excluir() {
        Dominio delete = service.consultarByCodigo(dominio.getCodigo());
        assertNotNull(delete);
        service.excluir(delete.getCodigo());

        Dominio found = service.consultarByCodigo(dominio.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }
}