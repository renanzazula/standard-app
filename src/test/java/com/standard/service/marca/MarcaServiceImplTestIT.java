package com.standard.service.marca;

import com.standard.BaseTest;
import com.standard.domain.Marca;
import com.standard.repository.MarcaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MarcaServiceImplTestIT extends BaseTest {

    @Autowired
    private MarcaRepository repository;

    private MarcaService service;

    @BeforeEach
    public void setUp() {
        service = new MarcaServiceImpl(repository);
        setUpMarca();
        marca = service.incluir(marca);
    }

    @Test
    public void incluir() {
        Marca saved = service.incluir(marca);
        assertNotNull(saved);

        Marca found = service.consultarByCodigo(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Marca update = service.consultarByCodigo(marca.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Marca updated = service.alterar(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Marca> found = service.consultar();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Marca found = service.consultarByCodigo(marca.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), marca.getCodigo());
    }

    @Test
    public void excluir() {

        Marca delete = service.consultarByCodigo(marca.getCodigo());
        assertNotNull(delete);

        service.excluir(delete.getCodigo());

        Marca found = service.consultarByCodigo(marca.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }
}