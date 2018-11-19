package com.standard.service.fornecedor;

import com.standard.BaseTest;
import com.standard.domain.Fornecedor;
import com.standard.repository.FornecedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FornecedorServiceImplTestIT extends BaseTest {

    @Autowired
    private FornecedorRepository repository;

    private FornecedorService service;

    @BeforeEach
    public void setUp() {
        service = new FornecedorServiceImpl(repository);
        setUpFornecedor();
        fornecedor = service.incluir(fornecedor);
    }

    @Test
    public void incluir() {
        Fornecedor saved = service.incluir(fornecedor);
        assertNotNull(saved);

        Fornecedor found = service.consultarByCodigo(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Fornecedor update = service.consultarByCodigo(fornecedor.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Fornecedor updated = service.alterar(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Fornecedor> found = service.consultar();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Fornecedor found = service.consultarByCodigo(fornecedor.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), fornecedor.getCodigo());
    }

    @Test
    public void excluir() {

        Fornecedor delete = service.consultarByCodigo(fornecedor.getCodigo());
        assertNotNull(delete);

        service.excluir(delete.getCodigo());

        Fornecedor found = service.consultarByCodigo(fornecedor.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }

}