package com.standard.service.payback;

import com.standard.BaseTest;
import com.standard.domain.Pos;
import com.standard.domain.Customer;
import com.standard.domain.Recebimento;

import com.standard.entity.CustomerEntity;
import com.standard.repository.PosRepository;
import com.standard.repository.CustomerRepository;
import com.standard.repository.PayBackRepository;
import com.standard.service.pos.PosServiceImpl;
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
class PayBackServiceImplTest extends BaseTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PayBackRepository payBackRepository;

    @Autowired
    private PosRepository posRepository;

    private PayBackServiceImpl recebimentoService;
    private PosServiceImpl caixaService;

    // Fixme: later
    CustomerEntity clienteEntity = null;

    Recebimento recebimento = null;

    @BeforeEach
    void setUp() {
        caixaService =  new PosServiceImpl(posRepository);
        recebimentoService = new PayBackServiceImpl(payBackRepository, posRepository, customerRepository);

        clienteEntity = new CustomerEntity();
        customerRepository.save(clienteEntity);

        Customer customer = new Customer();
        customer.setId(clienteEntity.getId());

        pos = new Pos();
        pos.setOpenAmount(5.0);
        pos = caixaService.openPos(pos);

        recebimento = new Recebimento();
        recebimento.setName(NOME);
        recebimento.setDescription(DESCRICAO);
        recebimento.setValor(10.0);
        recebimento.setPos(pos);
        recebimento.setCustomer(customer);

    }

    @Test
    void incluir() {
        Recebimento saved = recebimentoService.create(recebimento);
        assertEquals(saved.getName(), recebimento.getName());
        assertEquals(saved.getDescription(), recebimento.getDescription());
        assertEquals(saved.getValor(), recebimento.getValor());

        // TODO: Caixa

        // TODO: Cliente

    }

    @Test
    void alterar() {
        recebimento = recebimentoService.create(recebimento);
        Recebimento toUpdate = recebimentoService.getById(recebimento.getId());
        toUpdate.setName(NOME_UPDATE);
        toUpdate.setDescription(DESCRICAO_UPDATE);
        toUpdate.setValor(15.0);

        Recebimento toUpdated = recebimentoService.update(recebimento.getId(), toUpdate);
        assertEquals(toUpdate.getName(), toUpdated.getName());
        assertEquals(toUpdate.getDescription(), toUpdated.getDescription());
        assertEquals(toUpdate.getValor(), toUpdated.getValor());
    }

    @Test
    void excluir() {
        recebimento = recebimentoService.create(recebimento);
        recebimentoService.delete(recebimento.getId());
    }

    @Test
    void consultar() {
        List<Recebimento> list = recebimentoService.findAll();
        assertNotNull(list);
    }

    @Test
    void consultarByCodigo() {
        recebimento = recebimentoService.create(recebimento);
        Recebimento found = recebimentoService.getById(recebimento.getId());
        assertEquals(found.getName(), recebimento.getName());
        assertEquals(found.getDescription(), recebimento.getDescription());
        assertEquals(found.getValor(), recebimento.getValor());
    }
}
