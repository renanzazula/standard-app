package com.standard.service.retirada;

import com.standard.BaseTest;
import com.standard.domain.Pos;
import com.standard.domain.Retirada;
import com.standard.repository.PosRepository;
import com.standard.repository.RetiradaRepository;
import com.standard.service.caixa.PosServiceImpl;
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
public class RetiradaServiceImplTestIT extends BaseTest {

    @Autowired
    private RetiradaRepository repository;
    @Autowired
    private PosRepository posRepository;

    private PosServiceImpl caixaService;
    private RetiradaServiceImpl service;

    private Retirada retirada;

    @BeforeEach
    void setUp() {
        caixaService =  new PosServiceImpl(posRepository);

        pos = new Pos();
        pos.setValorInicial(5.0);
        pos = caixaService.openPos(pos);

        service = new RetiradaServiceImpl(repository, posRepository);
        retirada = new Retirada();
        retirada.setDescricao(DESCRICAO);
        retirada.setValor(10.0);
        retirada.setPos(pos);
        retirada = service.incluir(retirada);
    }

    @Test
    void incluir() {
        Retirada saved = service.incluir(retirada);
        assertNotNull(saved);

        Retirada found = service.consultarByCodigo(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getDescricao(), saved.getDescricao());
        assertEquals(found.getValor(), saved.getValor());

    }

    @Test
    void alterar() {
        Retirada update = service.consultarByCodigo(retirada.getCodigo());
        update.setValor(20.0);
        update.setDescricao(DESCRICAO_UPDATE);
        update.setPos(pos);

        Retirada updated = service.alterar(retirada.getCodigo(), update);
        assertEquals(updated.getDescricao(), update.getDescricao());
        assertEquals(updated.getValor(), update.getValor());

    }

    @Test
    void consultarByCodigo() {
        Retirada found = service.consultarByCodigo(retirada.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), retirada.getCodigo());
    }

    @Test
    void consultar() {
        List<Retirada> found = service.consultar();
        assertNotNull(found);
    }

    @Test
    void excluir() {
        Retirada delete = service.consultarByCodigo(retirada.getCodigo());
        assertNotNull(delete);
        service.excluir(delete.getCodigo());
        Retirada found = service.consultarByCodigo(retirada.getCodigo());
        assertNull(found.getValor());
        assertNull(found.getDescricao());
    }
}