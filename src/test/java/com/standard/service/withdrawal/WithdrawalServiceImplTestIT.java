package com.standard.service.withdrawal;

import com.standard.BaseTest;
import com.standard.domain.Pos;
import com.standard.domain.Withdrawal;
import com.standard.repository.PosRepository;
import com.standard.repository.WithdrawalRepository;
import com.standard.service.pos.PosServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("/scripts/dataset.sql")
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
public class WithdrawalServiceImplTestIT extends BaseTest {

    @Autowired
    private WithdrawalRepository repository;
    @Autowired
    private PosRepository posRepository;

    private WithdrawalServiceImpl service;

    private PosServiceImpl orderService;

    private Withdrawal withdrawal;

    @BeforeEach
    void setUp() {
        orderService =  new PosServiceImpl(posRepository);

        pos = new Pos();
        pos.setOpenAmount(5.0);
        pos = orderService.openPos(pos);

        service = new WithdrawalServiceImpl(repository, posRepository);
        withdrawal = new Withdrawal();
        withdrawal.setDescription(DESCRIPTION);
        withdrawal.setAmount(10.0);
        withdrawal.setPos(pos);
        withdrawal = service.create(withdrawal);
    }

    @Test
    void create() {
        Withdrawal saved = service.create(withdrawal);
        assertNotNull(saved);

        Withdrawal found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getDescription(), saved.getDescription());
        assertEquals(found.getAmount(), saved.getAmount());

    }

    @Test
    void update() {
        Withdrawal update = service.findById(withdrawal.getId());
        update.setAmount(20.0);
        update.setDescription(DESCRIPTION_UPDATE);
        update.setPos(pos);

        Withdrawal updated = service.update(withdrawal.getId(), update);
        assertEquals(updated.getDescription(), update.getDescription());
        assertEquals(updated.getAmount(), update.getAmount());

    }

    @Test
    void findById() {
        Withdrawal found = service.findById(withdrawal.getId());
        assertNotNull(found);
        assertEquals(found.getId(), withdrawal.getId());
    }

    @Test
    void findAll() {
        List<Withdrawal> found = service.findAll();
        assertNotNull(found);
    }


    @Test
    void delete() {
        Withdrawal delete = service.findById(withdrawal.getId());
        assertNotNull(delete);
        service.delete(delete.getId());
        Assertions.assertThrows(EntityNotFoundException.class, () -> { service.findById(withdrawal.getId()); });
    }
}
