package com.standard.service.dominio;

import com.standard.BaseTest;
import com.standard.domain.Dominio;
import com.standard.repository.DominioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DominioServiceImplTestIT extends BaseTest {

    @Autowired
    private DominioRepository repository;

    private DominioService service;

    @Before
    public void setUp() {
        service = new DominioServiceImpl(repository);
        setUpDominio();
        dominio = service.incluir(dominio);
    }

    @Test
    public void incluir() {
        Dominio saved = service.incluir(dominio);
        Assert.assertNotNull(saved);

        Dominio found = service.consultarByCodigo(saved.getCodigo());
        assertDominios(saved, found);
    }

    @Test
    public void alterar() {
        Dominio update = service.consultarByCodigo(dominio.getCodigo());
        Assert.assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);
        update.setChecked(false);

        Dominio updated = service.alterar(update.getCodigo(), update);
        assertDominios(update, updated);
    }

    @Test
    public void consultar() {
        List<Dominio> found = service.consultar();
        Assert.assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Dominio found = service.consultarByCodigo(dominio.getCodigo());
        Assert.assertNotNull(found);
        assertDominios(found, dominio);
    }

    @Test
    public void excluir() {
        Dominio delete = service.consultarByCodigo(dominio.getCodigo());
        Assert.assertNotNull(delete);
        service.excluir(delete.getCodigo());

        Dominio found = service.consultarByCodigo(dominio.getCodigo());
        Assert.assertNull(found.getCodigo());
        Assert.assertNull(found.getNome());
        Assert.assertNull(found.getDescricao());
    }
}