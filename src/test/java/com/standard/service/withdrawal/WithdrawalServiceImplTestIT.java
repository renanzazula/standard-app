package com.standard.service.withdrawal;

import com.standard.BaseTest;
import com.standard.domain.Pos;
import com.standard.domain.Withdrawal;
import com.standard.repository.PosRepository;
import com.standard.repository.WithdrawalRepository;
import com.standard.service.pos.PosServiceImpl;
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
public class WithdrawalServiceImplTestIT extends BaseTest {

    @Autowired
    private WithdrawalRepository repository;
    @Autowired
    private PosRepository posRepository;

    private PosServiceImpl caixaService;
    private WithdrawalServiceImpl service;

    private Withdrawal withdrawal;

    @BeforeEach
    void setUp() {
        caixaService =  new PosServiceImpl(posRepository);

        pos = new Pos();
        pos.setOpenAmount(5.0);
        pos = caixaService.openPos(pos);

        service = new WithdrawalServiceImpl(repository, posRepository);
        withdrawal = new Withdrawal();
        withdrawal.setDescription(DESCRICAO);
        withdrawal.setAmount(10.0);
        withdrawal.setPos(pos);
        withdrawal = service.create(withdrawal);
    }

    @Test
    void incluir() {
        Withdrawal saved = service.create(withdrawal);
        assertNotNull(saved);

        Withdrawal found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getDescription(), saved.getDescription());
        assertEquals(found.getAmount(), saved.getAmount());

    }

    @Test
    void alterar() {
        Withdrawal update = service.findById(withdrawal.getId());
        update.setAmount(20.0);
        update.setDescription(DESCRICAO_UPDATE);
        update.setPos(pos);

        Withdrawal updated = service.update(withdrawal.getId(), update);
        assertEquals(updated.getDescription(), update.getDescription());
        assertEquals(updated.getAmount(), update.getAmount());

    }

    @Test
    void consultarByCodigo() {
        Withdrawal found = service.findById(withdrawal.getId());
        assertNotNull(found);
        assertEquals(found.getId(), withdrawal.getId());
    }

    @Test
    void consultar() {
        List<Withdrawal> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    void excluir() {
        Withdrawal delete = service.findById(withdrawal.getId());
        assertNotNull(delete);
        service.delete(delete.getId());
        Withdrawal found = service.findById(withdrawal.getId());
        assertNull(found.getAmount());
        assertNull(found.getDescription());
    }
}
