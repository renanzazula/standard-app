package com.standard.service.FormaDePagamento;

import com.standard.BaseTest;
import com.standard.domain.FormasDePagamento;
import com.standard.repository.FormaDePagamentoRepository;
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
public class FormaDePagamentoServiceImplTestIT extends BaseTest {

    @Autowired
    private FormaDePagamentoRepository repository;

    private FormaDePagamentoService service;

    private FormasDePagamento obj = null;

    @Before
    public void setUp() {
        service = new FormaDePagamentoServiceImpl(repository);
        obj = new FormasDePagamento();
        obj.setNome(NOME);
        obj.setDescricao(DESCRIÇAO);
        obj.setPorcentagemDesconto(PORCENTAGEM_DESCONTO);
        obj = service.incluir(obj);
    }

    @Test
    public void incluir() {
        FormasDePagamento saved = service.incluir(obj);
        Assert.assertNotNull(saved);

        FormasDePagamento found = service.consultarByCodigo(saved.getCodigo());
        Assert.assertEquals(found.getCodigo(), saved.getCodigo());
        Assert.assertEquals(found.getNome(), saved.getNome());
        Assert.assertEquals(found.getDescricao(), saved.getDescricao());
        Assert.assertEquals(found.getPorcentagemDesconto(), saved.getPorcentagemDesconto());
    }

    @Test
    public void alterar() {
        FormasDePagamento update = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);
        update.setPorcentagemDesconto(PORCENTAGEM_DESCONTO);

        FormasDePagamento updated = service.alterar(update.getCodigo(), update);
        Assert.assertEquals(update.getCodigo(), updated.getCodigo());
        Assert.assertEquals(update.getNome(), updated.getNome());
        Assert.assertEquals(update.getDescricao(), updated.getDescricao());
        Assert.assertEquals(update.getPorcentagemDesconto(), updated.getPorcentagemDesconto());
    }

    @Test
    public void excluir() {
//        FormasDePagamento delete = service.consultarByCodigo(obj.getCodigo());
//        Assert.assertNotNull(delete);
//        service.excluir(delete.getCodigo());
//
//        FormasDePagamento found = service.consultarByCodigo(obj.getCodigo());
//        Assert.assertNull(found.getCodigo());
//        Assert.assertNull(found.getNome());
//        Assert.assertNull(found.getDescricao());
    }

    @Test
    public void consultar() {
        List<FormasDePagamento> found = service.consultar();
        Assert.assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        FormasDePagamento found = service.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getCodigo(), obj.getCodigo());
    }
}