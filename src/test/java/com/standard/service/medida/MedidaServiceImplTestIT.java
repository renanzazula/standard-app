package com.standard.service.medida;

import com.standard.BaseTest;
import com.standard.domain.Category;
import com.standard.domain.Marca;
import com.standard.domain.Medida;
import com.standard.domain.Subcategory;
import com.standard.repository.CategoryRepository;
import com.standard.repository.MarcaRepository;
import com.standard.repository.MedidaRepository;
import com.standard.repository.SubcategoriaRepository;
import com.standard.service.categoria.CategoryService;
import com.standard.service.categoria.CategoryServiceImpl;
import com.standard.service.marca.MarcaService;
import com.standard.service.marca.MarcaServiceImpl;
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

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedidaServiceImplTestIT extends BaseTest {


    @Autowired
    private MedidaRepository medidaRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    private MarcaService marcaService;
    private MedidaService medidaService;
    private SubcategoriaService subcategoriaService;
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        medidaService = new MedidaServiceImpl(medidaRepository, categoryRepository,
                subcategoriaRepository, marcaRepository);

        marcaService = new MarcaServiceImpl(marcaRepository);
        subcategoriaService = new SubcategoriaServiceImpl(subcategoriaRepository);
        categoryService = new CategoryServiceImpl(categoryRepository, subcategoriaRepository);

        setUpMarca();
        marca = marcaService.incluir(marca);

        setUpSubCategoria();
        subcategory = subcategoriaService.save(subcategory);

        setUpCategoria();
        category.setSubcategories(new ArrayList<>());
        category.getSubcategories().add(subcategory);
        category = categoryService.save(category);


        setUpItensTipoMedida();
        setUpMedida();
        medida.setSubcategory(subcategory);
        medida.setCategory(category);
        medida.setMarca(marca);
        medida.setItensTipoMedida(itensTipoMedida);

    }

    @Test
    public void incluir() {
        Medida medidaSave = medidaService.incluir(medida);
        assertEquals(medidaSave.getItensTipoMedida().size(), medida.getItensTipoMedida().size());

        medida = medidaService.consultarByCodigo(medidaSave.getCodigo());
        assertMarcaSubCategoriaCategoriaValor(medidaSave);

    }

    @Test
    public void alterar() {
        // todo:
    }

    @Test
    public void alterarMarca() {

        medida = medidaService.incluir(medida);

        Marca marcaUpdate = new Marca();
        marcaUpdate.setNome(NOME_UPDATE);
        marcaUpdate.setDescricao(DESCRICAO_UPDATE);
        marcaUpdate = marcaService.incluir(marcaUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(medida.getCodigo());
        toUpdate.setCategory(category);
        toUpdate.setSubcategory(subcategory);
        toUpdate.setMarca(marcaUpdate);

        Medida updated = medidaService.alterar(medida.getCodigo(), toUpdate);

        for (int i = 0; i < updated.getItensTipoMedida().size(); i++) {
            Marca marcaFound = updated.getItensTipoMedida().get(i).getMarca();
            assertMarca(marcaFound, marcaUpdate);

            assertNotEquals(marcaUpdate.getCodigo(), marca.getCodigo());
            assertNotEquals(marcaUpdate.getNome(), marca.getNome());
            assertNotEquals(marcaUpdate.getDescricao(), marca.getDescricao());
        }

    }

    @Test
    public void alterarSubCategoria() {

        medida = medidaService.incluir(medida);

        Subcategory subcategoryUpdate = new Subcategory();
        subcategoryUpdate.setNome(NOME_UPDATE);
        subcategoryUpdate.setDescricao(DESCRICAO_UPDATE);
        subcategoryUpdate = subcategoriaService.save(subcategoryUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(medida.getCodigo());
        toUpdate.setCategory(category);
        toUpdate.setSubcategory(subcategoryUpdate);
        toUpdate.setMarca(marca);

        Medida updated = medidaService.alterar(medida.getCodigo(), toUpdate);

        for (int i = 0; i < updated.getItensTipoMedida().size(); i++) {
            Subcategory subcategoryFound = updated.getItensTipoMedida().get(i).getSubcategory();
            assertSubCategoria(subcategoryFound, subcategoryUpdate);

            assertNotEquals(subcategoryUpdate.getCodigo(), subcategory.getCodigo());
            assertNotEquals(subcategoryUpdate.getNome(), subcategory.getNome());
            assertNotEquals(subcategoryUpdate.getDescricao(), subcategory.getDescricao());
        }

    }

    @Test
    public void alterarCategoria() {

        medida = medidaService.incluir(medida);

        Category categoryUpdate = new Category();
        categoryUpdate.setNome(NOME_UPDATE);
        categoryUpdate.setDescricao(DESCRICAO_UPDATE);
        categoryUpdate.setSubcategories(new ArrayList<>());
        categoryUpdate.getSubcategories().add(subcategory);
        categoryUpdate = categoryService.save(categoryUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(medida.getCodigo());
        toUpdate.setCategory(categoryUpdate);
        toUpdate.setSubcategory(subcategory);
        toUpdate.setMarca(marca);

        Medida updated = medidaService.alterar(medida.getCodigo(), toUpdate);

        for (int i = 0; i < updated.getItensTipoMedida().size(); i++) {
            Category categoryFound = updated.getItensTipoMedida().get(i).getCategory();
            assertCategoria(categoryFound, categoryUpdate);

            assertNotEquals(categoryFound.getCodigo(), category.getCodigo());
            assertNotEquals(categoryFound.getNome(), category.getNome());
            assertNotEquals(categoryFound.getDescricao(), category.getDescricao());
        }
    }

    @Test
    public void excluir() {
        medida = medidaService.incluir(medida);

        Medida delete = medidaService.consultarByCodigo(medida.getCodigo());
        assertNotNull(delete);

        medidaService.excluir(delete.getCodigo());

        Medida found = medidaService.consultarByCodigo(medida.getCodigo());
        assertNull(found.getCodigo());
        assertNull(found.getNome());
        assertNull(found.getDescricao());

    }

    @Test
    public void consultar() {
        List<Medida> medidas = medidaService.consultar();
        assertNotNull(medidas);
    }

    @Test
    public void consultarByCodigo() {
        medida = medidaService.incluir(medida);
        Medida medidaFound = medidaService.consultarByCodigo(medida.getCodigo());
        assertEquals(medidaFound.getCodigo(), medida.getCodigo());
        assertEquals(medidaFound.getNome(), medida.getNome());
        assertEquals(medidaFound.getDescricao(), medida.getDescricao());
        assertMarcaSubCategoriaCategoriaValor(medidaFound);
    }

    @Test
    public void consultarByCategoriaSubCategoriaMarca() {
        // TODO
    }
}