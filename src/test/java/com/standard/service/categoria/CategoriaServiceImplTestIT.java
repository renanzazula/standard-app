package com.standard.service.categoria;

import com.standard.BaseTest;
import com.standard.domain.Categoria;
import com.standard.domain.SubCategoria;
import com.standard.repository.CategoriaRepository;
import com.standard.repository.SubCategoriaRepository;
import com.standard.service.subCategoria.SubCategoriaService;
import com.standard.service.subCategoria.SubCategoriaServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaServiceImplTestIT extends BaseTest {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    private CategoriaService service;

    private Categoria obj = null;

    @Before
    public void setUp() {
        service = new CategoriaServiceImpl(repository, subCategoriaRepository);
        SubCategoriaService subCategoriaService = new SubCategoriaServiceImpl(subCategoriaRepository);

        List<SubCategoria> subCategorias = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            SubCategoria subCategoria = new SubCategoria();
            subCategoria.setNome(NOME + "_" + i);
            subCategoria.setDescricao(DESCRICAO + "_" + i);
            subCategorias.add(subCategoriaService.incluir(subCategoria));
        }

        obj = new Categoria();
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
        obj.setSubCategorias(subCategorias);
        obj = service.incluir(obj);
    }

    @Test
    public void incluir() {
        Categoria saved = service.incluir(obj);
        Assert.assertNotNull(saved);

        Categoria found = service.consultarByCodigo(saved.getCodigo());
        Assert.assertEquals(found.getCodigo(), saved.getCodigo());
        Assert.assertEquals(found.getNome(), saved.getNome());
        Assert.assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Categoria update = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Categoria updated = service.alterar(update.getCodigo(), update);
        Assert.assertEquals(update.getCodigo(), updated.getCodigo());
        Assert.assertEquals(update.getNome(), updated.getNome());
        Assert.assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Categoria> found = service.consultar();
        Assert.assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Categoria found = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getCodigo(), obj.getCodigo());
    }

    @Test
    public void excluir() {
        Categoria delete = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(delete);
        service.excluir(delete.getCodigo());

        Categoria found = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNull(found.getCodigo());
        Assert.assertNull(found.getNome());
        Assert.assertNull(found.getDescricao());
    }
}