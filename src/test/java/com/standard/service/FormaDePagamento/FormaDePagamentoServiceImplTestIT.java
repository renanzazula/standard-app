package com.standard.service.FormaDePagamento;

import com.standard.BaseTest;
import com.standard.domain.FormasDePagamento;
import com.standard.repository.FormaDePagamentoRepository;
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
public class FormaDePagamentoServiceImplTestIT extends BaseTest {

    @Autowired
    private FormaDePagamentoRepository repository;

    private FormaDePagamentoService service;

    @BeforeEach
    public void setUp() {
        service = new FormaDePagamentoServiceImpl(repository);
        setUpFormasDePagamento();
        formasDePagamento = service.incluir(formasDePagamento);
    }

    @Test
    public void incluir() {
        FormasDePagamento saved = service.incluir(formasDePagamento);
        assertNotNull(saved);
        FormasDePagamento found = service.consultarByCodigo(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
        assertEquals(found.getPorcentagemDesconto(), saved.getPorcentagemDesconto());
    }

    @Test
    public void alterar() {
        FormasDePagamento update = service.consultarByCodigo(formasDePagamento.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);
        update.setPorcentagemDesconto(PORCENTAGEM_DESCONTO);

        FormasDePagamento updated = service.alterar(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
        assertEquals(update.getPorcentagemDesconto(), updated.getPorcentagemDesconto());
    }

    @Test
    public void consultar() {
        List<FormasDePagamento> found = service.consultar();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        FormasDePagamento found = service.consultarByCodigo(formasDePagamento.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), formasDePagamento.getCodigo());
    }

    @Test
    public void excluir() {

        FormasDePagamento delete = service.consultarByCodigo(formasDePagamento.getCodigo());
        assertNotNull(delete);

        service.excluir(delete.getCodigo());

        FormasDePagamento found = service.consultarByCodigo(formasDePagamento.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
        assertNull(found.getPorcentagemDesconto());
    }
}