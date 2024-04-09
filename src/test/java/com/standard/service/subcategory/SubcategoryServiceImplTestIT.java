package com.standard.service.subcategory;

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
        subcategory.setName(NOME);
        subcategory.setDescription(DESCRICAO);
        subcategory = service.create(subcategory);
    }

    @Test
    public void incluir() {
        Subcategory saved = service.create(subcategory);
        
        assertNotNull(saved);

        Subcategory found = service.findById(saved.getId());
        assertEquals(found.getId(), saved.getId());
        assertEquals(found.getName(), saved.getName());
        assertEquals(found.getDescription(), saved.getDescription());
    }

    @Test
    public void alterar() {
        Subcategory update = service.findById(subcategory.getId());
        assertNotNull(update);
        update.setName(NOME_UPDATE);
        update.setDescription(DESCRICAO_UPDATE);

        Subcategory updated = service.update(update.getId(), update);
        assertEquals(update.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getDescription(), updated.getDescription());
    }

    @Test
    public void consultar() {
        List<Subcategory> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Subcategory found = service.findById(subcategory.getId());
        assertNotNull(found);
        assertEquals(found.getId(), subcategory.getId());
    }

    @Test
    public void excluir() {

        Subcategory delete = service.findById(subcategory.getId());
        assertNotNull(delete);

        service.delete(delete.getId());

        Subcategory found = service.findById(subcategory.getId());
        assertNull(found.getId());
        assertNull(found.getName());
        assertNull(found.getDescription());
    }
    
}
