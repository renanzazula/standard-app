package com.standard.service.subcategoria;

import com.standard.BaseTest;
import com.standard.domain.Subcategory;
import com.standard.repository.SubcategoryRepository;
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
public class SubcategoryServiceImplTestIT extends BaseTest {

    @Autowired
    private SubcategoryRepository repository;

    private SubcategoryService service;



    @BeforeEach
    public void setUp() {
        service = new SubcategoryServiceImpl(repository);
        subcategory = new Subcategory();
        subcategory.setNome(NOME);
        subcategory.setDescricao(DESCRICAO);
        subcategory = service.save(subcategory);
    }

    @Test
    public void incluir() {
        Subcategory saved = service.save(subcategory);
        
        assertNotNull(saved);

        Subcategory found = service.findById(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Subcategory update = service.findById(subcategory.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Subcategory updated = service.update(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Subcategory> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Subcategory found = service.findById(subcategory.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), subcategory.getCodigo());
    }

    @Test
    public void excluir() {

        Subcategory delete = service.findById(subcategory.getCodigo());
        assertNotNull(delete);

        service.delete(delete.getCodigo());

        Subcategory found = service.findById(subcategory.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }
    
}
