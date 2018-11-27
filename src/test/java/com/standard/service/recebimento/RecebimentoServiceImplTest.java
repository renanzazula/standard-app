package com.standard.service.recebimento;

import com.standard.BaseTest;
import com.standard.domain.Caixa;
import com.standard.domain.Cliente;
import com.standard.domain.Recebimento;
import com.standard.entity.ClienteEntity;
import com.standard.repository.CaixaRepository;
import com.standard.repository.ClienteRepository;
import com.standard.repository.RecebimentoRepository;
import com.standard.service.caixa.CaixaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecebimentoServiceImplTest extends BaseTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RecebimentoRepository recebimentoRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    private RecebimentoServiceImpl recebimentoService;
    private CaixaServiceImpl caixaService;

    // Fixme: later
    ClienteEntity clienteEntity = null;

    Recebimento recebimento = null;

    @BeforeEach
    void setUp() {
        caixaService =  new CaixaServiceImpl(caixaRepository);
        recebimentoService = new RecebimentoServiceImpl(recebimentoRepository, caixaRepository, clienteRepository);

        clienteEntity = new ClienteEntity();
        clienteRepository.save(clienteEntity);

        Cliente cliente = new Cliente();
        cliente.setCodigo(clienteEntity.getCodigo());

        caixa = new Caixa();
        caixa.setValorInicial(5.0);
        caixa = caixaService.abrirCaixa(caixa);

        recebimento = new Recebimento();
        recebimento.setNome(NOME);
        recebimento.setDescricao(DESCRICAO);
        recebimento.setValor(10.0);
        recebimento.setCaixa(caixa);
        recebimento.setCliente(cliente);

    }

    @Test
    void incluir() {
        Recebimento saved = recebimentoService.incluir(recebimento);
        assertEquals(saved.getNome(), recebimento.getNome());
        assertEquals(saved.getDescricao(), recebimento.getDescricao());
        assertEquals(saved.getValor(), recebimento.getValor());

        // TODO: Caixa

        // TODO: Cliente

    }

    @Test
    void alterar() {
        recebimento = recebimentoService.incluir(recebimento);
        Recebimento toUpdate = recebimentoService.consultarByCodigo(recebimento.getCodigo());
        toUpdate.setNome(NOME_UPDATE);
        toUpdate.setDescricao(DESCRICAO_UPDATE);
        toUpdate.setValor(15.0);

        Recebimento toUpdated = recebimentoService.alterar(recebimento.getCodigo(), toUpdate);
        assertEquals(toUpdate.getNome(), toUpdated.getNome());
        assertEquals(toUpdate.getDescricao(), toUpdated.getDescricao());
        assertEquals(toUpdate.getValor(), toUpdated.getValor());
    }

    @Test
    void excluir() {
        recebimento = recebimentoService.incluir(recebimento);
        recebimentoService.excluir(recebimento.getCodigo());
    }

    @Test
    void consultar() {
        List<Recebimento> list = recebimentoService.consultar();
        assertNotNull(list);
    }

    @Test
    void consultarByCodigo() {
        recebimento = recebimentoService.incluir(recebimento);
        Recebimento found = recebimentoService.consultarByCodigo(recebimento.getCodigo());
        assertEquals(found.getNome(), recebimento.getNome());
        assertEquals(found.getDescricao(), recebimento.getDescricao());
        assertEquals(found.getValor(), recebimento.getValor());
    }
}