package com.standard.service.categoria;

import com.standard.BaseTest;
import com.standard.domain.Categoria;
import com.standard.domain.Subcategoria;
import com.standard.repository.CategoriaRepository;
import com.standard.repository.SubcategoriaRepository;
import com.standard.service.subcategoria.SubcategoriaService;
import com.standard.service.subcategoria.SubcategoriaServiceImpl;
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
public class CategoriaServiceImplTestIT extends BaseTest {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    private CategoriaService service;

    private Categoria obj = null;

    @BeforeEach
    public void setUp() {
        service = new CategoriaServiceImpl(repository, subcategoriaRepository);
        SubcategoriaService subcategoriaService = new SubcategoriaServiceImpl(subcategoriaRepository);

        List<Subcategoria> subcategorias = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Subcategoria subcategoria = new Subcategoria();
            subcategoria.setNome(NOME + "_" + i);
            subcategoria.setDescricao(DESCRICAO + "_" + i);
            subcategorias.add(subcategoriaService.incluir(subcategoria));
        }

        obj = new Categoria();
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
        obj.setSubcategorias(subcategorias);
        obj = service.incluir(obj);
    }

    @Test
    public void incluir() {
        Categoria saved = service.incluir(obj);
        assertNotNull(saved);

        Categoria found = service.consultarByCodigo(saved.getCodigo());
        assertEquals(found.getCodigo(), saved.getCodigo());
        assertEquals(found.getNome(), saved.getNome());
        assertEquals(found.getDescricao(), saved.getDescricao());
    }

    @Test
    public void alterar() {
        Categoria update = service.consultarByCodigo(obj.getCodigo());
        assertNotNull(update);
        update.setNome(NOME_UPDATE);
        update.setDescricao(DESCRICAO_UPDATE);

        Categoria updated = service.alterar(update.getCodigo(), update);
        assertEquals(update.getCodigo(), updated.getCodigo());
        assertEquals(update.getNome(), updated.getNome());
        assertEquals(update.getDescricao(), updated.getDescricao());
    }

    @Test
    public void consultar() {
        List<Categoria> found = service.consultar();
        assertNotNull(found);
    }

    @Test
    public void consultarByCodigo() {
        Categoria found = service.consultarByCodigo(obj.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), obj.getCodigo());
    }

    @Test
    public void excluir() {
        Categoria delete = service.consultarByCodigo(obj.getCodigo());
        assertNotNull(delete);
        service.excluir(delete.getCodigo());

        Categoria found = service.consultarByCodigo(obj.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());
    }
}