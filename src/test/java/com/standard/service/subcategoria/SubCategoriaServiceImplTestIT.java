package com.standard.service.subcategoria;

import com.standard.BaseTest;
import com.standard.domain.SubCategoria;
import com.standard.repository.SubCategoriaRepository;
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
public class SubCategoriaServiceImplTestIT extends BaseTest {

    @Autowired
    private SubCategoriaRepository repository;

    private SubCategoriaService service;



    @BeforeEach
    public void setUp() {
        service = new SubCategoriaServiceImpl(repository);
        subCategoria = new SubCategoria();
        subCategoria.setNome(NOME);
        subCategoria.setDescricao(DESCRICAO);
        subCategoria = service.incluir(subCategoria);
    }

    @Test
    public void incluir() {
        SubCategoria saved = service.incluir(subCategoria);
        
        assertNotNull(saved);

        SubCategoria found = service.consultarByCodigo(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        SubCategoria update = service.consultarByCodigo(subCategoria.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        SubCategoria updated = service.alterar(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<SubCategoria> found = service.consultar();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        SubCategoria found = service.consultarByCodigo(subCategoria.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), subCategoria.getCodigo());
    }

    @Test
    public void excluir() {

        SubCategoria delete = service.consultarByCodigo(subCategoria.getCodigo());
        assertNotNull(delete);

        service.excluir(delete.getCodigo());

        SubCategoria found = service.consultarByCodigo(subCategoria.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }
    
}