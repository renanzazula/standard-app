package com.standard.service.fornecedor;

import com.standard.BaseTest;
import com.standard.domain.Fornecedor;
import com.standard.repository.FornecedorRepository;
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
public class FornecedorServiceImplTestIT extends BaseTest {

    @Autowired
    private FornecedorRepository repository;

    private FornecedorService service;

    @Before
    public void setUp() {
        service = new FornecedorServiceImpl(repository);
        setUpFornecedor();
        fornecedor = service.incluir(fornecedor);
    }

    @Test
    public void incluir() {
        Fornecedor saved = service.incluir(fornecedor);
        Assert.assertNotNull(saved);

        Fornecedor found = service.consultarByCodigo(saved.getCodigo());
        Assert.assertEquals(found.getCodigo(), saved.getCodigo());
        Assert.assertEquals(found.getNome(), saved.getNome());
        Assert.assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Fornecedor update = service.consultarByCodigo(fornecedor.getCodigo());
        Assert.assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Fornecedor updated = service.alterar(update.getCodigo(), update);
        Assert.assertEquals(update.getCodigo(), updated.getCodigo());
        Assert.assertEquals(update.getNome(), updated.getNome());
        Assert.assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Fornecedor> found = service.consultar();
        Assert.assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Fornecedor found = service.consultarByCodigo(fornecedor.getCodigo());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getCodigo(), fornecedor.getCodigo());
    }

    @Test
    public void excluir() {

        Fornecedor delete = service.consultarByCodigo(fornecedor.getCodigo());
        Assert.assertNotNull(delete);

        service.excluir(delete.getCodigo());

        Fornecedor found = service.consultarByCodigo(fornecedor.getCodigo());
        Assert.assertNull(found.getCodigo());
        Assert.assertNull(found.getNome());
        Assert.assertNull(found.getDescricao());
    }

}