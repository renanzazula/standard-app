package com.standard.service.recebimento;

import com.standard.BaseTest;
import com.standard.domain.Pos;
import com.standard.domain.Customer;
import com.standard.domain.Recebimento;

import com.standard.entity.CustomerEntity;
import com.standard.repository.PosRepository;
import com.standard.repository.CustomerRepository;
import com.standard.repository.RecebimentoRepository;
import com.standard.service.caixa.PosServiceImpl;
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
    private CustomerRepository customerRepository;

    @Autowired
    private RecebimentoRepository recebimentoRepository;

    @Autowired
    private PosRepository posRepository;

    private RecebimentoServiceImpl recebimentoService;
    private PosServiceImpl caixaService;

    // Fixme: later
    CustomerEntity clienteEntity = null;

    Recebimento recebimento = null;

    @BeforeEach
    void setUp() {
        caixaService =  new PosServiceImpl(posRepository);
        recebimentoService = new RecebimentoServiceImpl(recebimentoRepository, posRepository, customerRepository);

        clienteEntity = new CustomerEntity();
        customerRepository.save(clienteEntity);

        Customer customer = new Customer();
        customer.setId(clienteEntity.getCodigo());

        pos = new Pos();
        pos.setValorInicial(5.0);
        pos = caixaService.openPos(pos);

        recebimento = new Recebimento();
        recebimento.setNome(NOME);
        recebimento.setDescricao(DESCRICAO);
        recebimento.setValor(10.0);
        recebimento.setPos(pos);
        recebimento.setCustomer(customer);

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