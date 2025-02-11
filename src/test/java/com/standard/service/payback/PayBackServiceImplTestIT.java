package com.standard.service.payback;

import com.standard.BaseTest;
import com.standard.domain.Customer;
import com.standard.domain.PayBack;
import com.standard.domain.Pos;
import com.standard.entity.CustomerEntity;
import com.standard.repository.CustomerRepository;
import com.standard.repository.PayBackRepository;
import com.standard.repository.PosRepository;
import com.standard.service.pos.PosService;
import com.standard.service.pos.PosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class PayBackServiceImplTestIT extends BaseTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PayBackRepository payBackRepository;

    @Autowired
    private PosRepository posRepository;

    private PayBackService payBackService;

    // Fixme: later
    CustomerEntity customerEntity = null;

    PayBack payBack = null;

    @BeforeEach
    void setUp() {
        PosService posService = new PosServiceImpl(posRepository);
        payBackService = new PayBackServiceImpl(payBackRepository, posRepository, customerRepository);

        customerEntity = new CustomerEntity();
        customerRepository.save(customerEntity);

        Customer customer = new Customer();
        customer.setId(customerEntity.getId());

        pos = new Pos();
        pos.setOpenAmount(5.0);
        pos = posService.openPos(pos);

        payBack = new PayBack();
        payBack.setName(NAME);
        payBack.setDescription(DESCRIPTION);
        payBack.setValor(10.0);
        payBack.setPos(pos);
        payBack.setCustomer(customer);

    }

    @Test
    void create() {
        PayBack saved = payBackService.create(payBack);
        assertEquals(saved.getName(), payBack.getName());
        assertEquals(saved.getDescription(), payBack.getDescription());
        assertEquals(saved.getValor(), payBack.getValor());

        // TODO: Caixa

        // TODO: Cliente

    }

    @Test
    void update() {
        payBack = payBackService.create(payBack);
        PayBack toUpdate = payBackService.getById(payBack.getId());
        toUpdate.setName(NAME_UPDATE);
        toUpdate.setDescription(DESCRIPTION_UPDATE);
        toUpdate.setValor(15.0);

        PayBack toUpdated = payBackService.update(payBack.getId(), toUpdate);
        assertEquals(toUpdate.getName(), toUpdated.getName());
        assertEquals(toUpdate.getDescription(), toUpdated.getDescription());
        assertEquals(toUpdate.getValor(), toUpdated.getValor());
    }

    @Test
    void delete() {
        payBack = payBackService.create(payBack);
        payBackService.delete(payBack.getId());
        assertNull(payBackService.getById(payBack.getId()));
    }

    @Test
    void findAll() {
        List<PayBack> list = payBackService.findAll();
        assertNotNull(list);
    }

    @Test
    void getById() {
        payBack = payBackService.create(payBack);
        PayBack found = payBackService.getById(payBack.getId());
        assertEquals(found.getName(), payBack.getName());
        assertEquals(found.getDescription(), payBack.getDescription());
        assertEquals(found.getValor(), payBack.getValor());
    }
}
