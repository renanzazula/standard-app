package com.standard.service.marca;

import com.standard.BaseTest;
import com.standard.domain.Marca;
import com.standard.repository.MarcaRepository;
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
public class MarcaServiceImplTestIT extends BaseTest {

    @Autowired
    private MarcaRepository repository;

    private MarcaService service;

    @Before
    public void setUp() {
        service = new MarcaServiceImpl(repository);
        setUpMarca();
        marca = service.incluir(marca);
    }

    @Test
    public void incluir() {
        Marca saved = service.incluir(marca);
        Assert.assertNotNull(saved);

        Marca found = service.consultarByCodigo(saved.getCodigo());
        Assert.assertEquals(found.getCodigo(), saved.getCodigo());
        Assert.assertEquals(found.getNome(), saved.getNome());
        Assert.assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Marca update = service.consultarByCodigo(marca.getCodigo());
        Assert.assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Marca updated = service.alterar(update.getCodigo(), update);
        Assert.assertEquals(update.getCodigo(), updated.getCodigo());
        Assert.assertEquals(update.getNome(), updated.getNome());
        Assert.assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Marca> found = service.consultar();
        Assert.assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Marca found = service.consultarByCodigo(marca.getCodigo());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getCodigo(), marca.getCodigo());
    }

    @Test
    public void excluir() {

        Marca delete = service.consultarByCodigo(marca.getCodigo());
        Assert.assertNotNull(delete);

        service.excluir(delete.getCodigo());

        Marca found = service.consultarByCodigo(marca.getCodigo());
        Assert.assertNull(found.getCodigo());
        Assert.assertNull(found.getNome());
        Assert.assertNull(found.getDescricao());
    }
}