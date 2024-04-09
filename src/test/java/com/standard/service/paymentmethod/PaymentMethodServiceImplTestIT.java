package com.standard.service.paymentmethod;

import com.standard.BaseTest;
import com.standard.domain.PaymentMethod;
import com.standard.repository.PaymentMethodRepository;
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
public class PaymentMethodServiceImplTestIT extends BaseTest {

    @Autowired
    private PaymentMethodRepository repository;

    private PaymentMethodService service;

    @BeforeEach
    public void setUp() {
        service = new PaymentMethodServiceImpl(repository);
        setUpFormasDePagamento();
        paymentMethod = service.create(paymentMethod);
    }

    @Test
    public void incluir() {
        PaymentMethod saved = service.create(paymentMethod);
        assertNotNull(saved);
        PaymentMethod found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
        assertEquals(found.getDiscountPercent(), saved.getDiscountPercent());
    }

    @Test
    public void alterar() {
        PaymentMethod update = service.findById(paymentMethod.getId());
        assertNotNull(update);
        update.setName(NOME_UPDATE);
        update.setDescription(DESCRICAO_UPDATE);
        update.setDiscountPercent(PORCENTAGEM_DESCONTO);

        PaymentMethod updated = service.update(update.getId(), update);
        assertEquals(update.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getDescription(), updated.getDescription());
        assertEquals(update.getDiscountPercent(), updated.getDiscountPercent());
    }

    @Test
    public void consultar() {
        List<PaymentMethod> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        PaymentMethod found = service.findById(paymentMethod.getId());
        assertNotNull(found);
        assertEquals(found.getId(), paymentMethod.getId());
    }

    @Test
    public void excluir() {

        PaymentMethod delete = service.findById(paymentMethod.getId());
        assertNotNull(delete);

        service.delete(delete.getId());

        PaymentMethod found = service.findById(paymentMethod.getId());
        assertNull(found.getId());
        assertNull(found.getName());
        assertNull(found.getDescription());
        assertNull(found.getDiscountPercent());
    }
}
