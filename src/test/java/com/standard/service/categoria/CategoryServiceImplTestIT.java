package com.standard.service.categoria;

import com.standard.BaseTest;
import com.standard.domain.Category;
import com.standard.domain.Subcategory;
import com.standard.repository.CategoryRepository;
import com.standard.repository.SubcategoryRepository;
import com.standard.service.subcategoria.SubcategoryService;
import com.standard.service.subcategoria.SubcategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryServiceImplTestIT extends BaseTest {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    private CategoryService service;

    private Category obj = null;

    @BeforeEach
    public void setUp() {
        service = new CategoryServiceImpl(repository, subcategoryRepository);
        SubcategoryService subcategoryService = new SubcategoryServiceImpl(subcategoryRepository);

        List<Subcategory> subcategories = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Subcategory subcategory = new Subcategory();
            subcategory.setNome(NOME + "_" + i);
            subcategory.setDescricao(DESCRICAO + "_" + i);
            subcategories.add(subcategoryService.save(subcategory));
        }

        obj = new Category();
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
        obj.setSubcategories(subcategories);
        obj = service.save(obj);
    }

    @Test
    public void incluir() {
        Category saved = service.save(obj);
        assertNotNull(saved);

        Category found = service.findById(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Category update = service.findById(obj.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Category updated = service.update(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Category> found = service.findAll();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Category found = service.findById(obj.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), obj.getCodigo());
    }

    @Test
    public void excluir() {
        Category delete = service.findById(obj.getCodigo());
        assertNotNull(delete);
        service.delete(delete.getCodigo());

        Category found = service.findById(obj.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }
}
