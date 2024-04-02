package com.standard.service.formaDePagamento;

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
        paymentMethod = service.save(paymentMethod);
    }

    @Test
    public void incluir() {
        PaymentMethod saved = service.save(paymentMethod);
        assertNotNull(saved);
        PaymentMethod found = service.findById(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
        assertEquals(found.getPorcentagemDesconto(), saved.getPorcentagemDesconto());
    }

    @Test
    public void alterar() {
        PaymentMethod update = service.findById(paymentMethod.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);
        update.setPorcentagemDesconto(PORCENTAGEM_DESCONTO);

        PaymentMethod updated = service.update(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
        assertEquals(update.getPorcentagemDesconto(), updated.getPorcentagemDesconto());
    }

    @Test
    public void consultar() {
        List<PaymentMethod> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        PaymentMethod found = service.findById(paymentMethod.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), paymentMethod.getCodigo());
    }

    @Test
    public void excluir() {

        PaymentMethod delete = service.findById(paymentMethod.getCodigo());
        assertNotNull(delete);

        service.delete(delete.getCodigo());

        PaymentMethod found = service.findById(paymentMethod.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
        assertNull(found.getPorcentagemDesconto());
    }
}
