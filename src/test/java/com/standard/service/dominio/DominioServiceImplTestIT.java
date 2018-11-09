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
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DominioServiceImplTestIT extends BaseTest {

    @Autowired
    private DominioRepository repository;

    private DominioService service;

    private Dominio obj = null;

    @Before
    public void setUp() {
        service = new DominioServiceImpl(repository);
        obj = new Dominio();
        obj.setNome(NOME);
        obj.setDescricao(DESCRIÇAO);
        obj = service.incluir(obj);
        obj.setChecked(true);
    }

    @Test
    public void incluir() {
        Dominio saved = service.incluir(obj);
        Assert.assertNotNull(saved);

        Dominio found = service.consultarByCodigo(saved.getCodigo());
        Assert.assertEquals(found.getCodigo(), saved.getCodigo());
        Assert.assertEquals(found.getNome(), saved.getNome());
        Assert.assertEquals(found.getDescricao(), saved.getDescricao());
        Assert.assertEquals(found.isChecked(), saved.isChecked());
    }

    @Test
    public void alterar() {
        Dominio update = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);
        update.setChecked(false);

        Dominio updated = service.alterar(update.getCodigo(), update);
        Assert.assertEquals(update.getCodigo(), updated.getCodigo());
        Assert.assertEquals(update.getNome(), updated.getNome());
        Assert.assertEquals(update.getDescricao(), updated.getDescricao());
        Assert.assertEquals(update.isChecked(), updated.isChecked());
    }

    @Test
    public void consultar() {
        List<Dominio> found = service.consultar();
        Assert.assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Dominio found = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getCodigo(), obj.getCodigo());
    }

    @Test
    public void excluir() {
        Dominio delete = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(delete);
        service.excluir(delete.getCodigo());

        Dominio found = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNull(found.getCodigo());
        Assert.assertNull(found.getNome());
        Assert.assertNull(found.getDescricao());
    }
}