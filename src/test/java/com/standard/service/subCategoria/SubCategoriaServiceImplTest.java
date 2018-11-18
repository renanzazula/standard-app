package com.standard.service.subCategoria;

import com.standard.BaseTest;
import com.standard.domain.SubCategoria;
import com.standard.repository.SubCategoriaRepository;
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
public class SubCategoriaServiceImplTest extends BaseTest {

    @Autowired
    private SubCategoriaRepository repository;

    private SubCategoriaService service;

    private SubCategoria obj = null;

    @Before
    public void setUp() {
        service = new SubCategoriaServiceImpl(repository);
        obj = new SubCategoria();
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
        obj = service.incluir(obj);
    }

    @Test
    public void incluir() {
        SubCategoria saved = service.incluir(obj);
        Assert.assertNotNull(saved);

        SubCategoria found = service.consultarByCodigo(saved.getCodigo());
        Assert.assertEquals(found.getCodigo(), saved.getCodigo());
        Assert.assertEquals(found.getNome(), saved.getNome());
        Assert.assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        SubCategoria update = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        SubCategoria updated = service.alterar(update.getCodigo(), update);
        Assert.assertEquals(update.getCodigo(), updated.getCodigo());
        Assert.assertEquals(update.getNome(), updated.getNome());
        Assert.assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<SubCategoria> found = service.consultar();
        Assert.assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        SubCategoria found = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getCodigo(), obj.getCodigo());
    }

    @Test
    public void excluir() {

        SubCategoria delete = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(delete);

        service.excluir(delete.getCodigo());

        SubCategoria found = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNull(found.getCodigo());
        Assert.assertNull(found.getNome());
        Assert.assertNull(found.getDescricao());
    }
    
}