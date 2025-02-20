package com.standard.service.withdrawal;

import com.standard.BaseTest;
import com.standard.domain.Pos;
import com.standard.domain.Withdrawal;
import com.standard.repository.PosRepository;
import com.standard.repository.WithdrawalRepository;
import com.standard.security.exceptions.WithdrawalNotFoundException;
import com.standard.service.pos.PosServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class WithdrawalServiceImplTestIT extends BaseTest {

    @Autowired
    private WithdrawalRepository repository;
    @Autowired
    private PosRepository posRepository;

    private WithdrawalServiceImpl withdrawalService;

	private Withdrawal withdrawal;

    @BeforeEach
    void setUp() {
		PosServiceImpl orderService = new PosServiceImpl(posRepository);

        pos = new Pos();
        pos.setOpenAmount(5.0);
        pos = orderService.openPos(pos);

        withdrawalService = new WithdrawalServiceImpl(repository, posRepository);
        withdrawal = new Withdrawal();
        withdrawal.setDescription(DESCRIPTION);
        withdrawal.setAmount(10.0);
        withdrawal.setPos(pos);
        withdrawal = withdrawalService.create(withdrawal);
    }

    @Test
    void create() {
        Withdrawal saved = withdrawalService.create(withdrawal);
        assertNotNull(saved);

        Withdrawal found = withdrawalService.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getDescription(), saved.getDescription());
        assertEquals(found.getAmount(), saved.getAmount());

    }

    @Test
    void update() {
        Withdrawal update = withdrawalService.findById(withdrawal.getId());
        update.setAmount(20.0);
        update.setDescription(DESCRIPTION_UPDATE);
        update.setPos(pos);

        Withdrawal updated = withdrawalService.update(withdrawal.getId(), update);
        assertEquals(updated.getDescription(), update.getDescription());
        assertEquals(updated.getAmount(), update.getAmount());

    }

    @Test
    void findById() {
        Withdrawal found = withdrawalService.findById(withdrawal.getId());
        assertNotNull(found);
        assertEquals(found.getId(), withdrawal.getId());
    }

    @Test
    void findAll() {
        List<Withdrawal> found = withdrawalService.findAll();
        assertNotNull(found);
    }

    @Test
    void delete()
    {
        Long withdrawalId = withdrawal.getId();
        Withdrawal delete = withdrawalService.findById(withdrawalId);
        assertNotNull(delete);

        withdrawalService.delete(delete.getId());
        Assertions.assertThrows(WithdrawalNotFoundException.class, () -> withdrawalService.findById(withdrawalId),"Withdrawal not found!!");
    }
}
