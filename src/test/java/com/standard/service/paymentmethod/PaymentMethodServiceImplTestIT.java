package com.standard.service.paymentmethod;

import com.standard.BaseTest;
import com.standard.domain.PaymentMethod;
import com.standard.enums.StatusEnum;
import com.standard.repository.PaymentMethodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Sql("/scripts/dataset.sql")
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
public class PaymentMethodServiceImplTestIT extends BaseTest {

    @Autowired
    private PaymentMethodRepository repository;

    private PaymentMethodService service;

    @BeforeEach
    public void setUp() {
        service = new PaymentMethodServiceImpl(repository);
        setUpPaymentMethod();
        paymentMethod = service.create(paymentMethod);
    }

    @Test
    public void create() {
        PaymentMethod saved = service.create(paymentMethod);
        assertNotNull(saved);
        PaymentMethod found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
        assertEquals(found.getDiscountPercent(), saved.getDiscountPercent());
    }

    @Test
    public void update() {
        PaymentMethod update = service.findById(paymentMethod.getId());
        assertNotNull(update);
        update.setName(NAME_UPDATE);
        update.setDescription(DESCRIPTION_UPDATE);
        update.setDiscountPercent(DISCOUNT_PERCENT);

        PaymentMethod updated = service.update(update.getId(), update);
        assertEquals(update.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getDescription(), updated.getDescription());
        assertEquals(update.getDiscountPercent(), updated.getDiscountPercent());
    }

    @Test
    public void findAll() {
        List<PaymentMethod> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void findById() {
        PaymentMethod found = service.findById(paymentMethod.getId());
        assertNotNull(found);
        assertEquals(found.getId(), paymentMethod.getId());
    }

    @Test
    public void delete() {

        PaymentMethod delete = service.findById(paymentMethod.getId());
        assertNotNull(delete);

        service.delete(delete.getId());

        PaymentMethod found = service.findById(paymentMethod.getId());
        assertEquals(found.getStatus(), StatusEnum.DISABLE.name());
    }
}
