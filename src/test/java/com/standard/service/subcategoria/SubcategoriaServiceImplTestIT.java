package com.standard.service.subcategoria;

import com.standard.BaseTest;
import com.standard.domain.Subcategoria;
import com.standard.repository.SubcategoriaRepository;
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
public class SubcategoriaServiceImplTestIT extends BaseTest {

    @Autowired
    private SubcategoriaRepository repository;

    private SubcategoriaService service;



    @BeforeEach
    public void setUp() {
        service = new SubcategoriaServiceImpl(repository);
        subcategoria = new Subcategoria();
        subcategoria.setNome(NOME);
        subcategoria.setDescricao(DESCRICAO);
        subcategoria = service.incluir(subcategoria);
    }

    @Test
    public void incluir() {
        Subcategoria saved = service.incluir(subcategoria);
        
        assertNotNull(saved);

        Subcategoria found = service.consultarByCodigo(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Subcategoria update = service.consultarByCodigo(subcategoria.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Subcategoria updated = service.alterar(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Subcategoria> found = service.consultar();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Subcategoria found = service.consultarByCodigo(subcategoria.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), subcategoria.getCodigo());
    }

    @Test
    public void excluir() {

        Subcategoria delete = service.consultarByCodigo(subcategoria.getCodigo());
        assertNotNull(delete);

        service.excluir(delete.getCodigo());

        Subcategoria found = service.consultarByCodigo(subcategoria.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }
    
}