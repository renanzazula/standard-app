package com.standard.service.medida;

import com.standard.BaseTest;
import com.standard.domain.Category;
import com.standard.domain.Brand;
import com.standard.domain.Measure;
import com.standard.domain.Subcategory;
import com.standard.repository.CategoryRepository;
import com.standard.repository.BrandRepository;
import com.standard.repository.MeasureRepository;
import com.standard.repository.SubcategoryRepository;
import com.standard.service.categoria.CategoryService;
import com.standard.service.categoria.CategoryServiceImpl;
import com.standard.service.marca.BrandService;
import com.standard.service.marca.BrandServiceImpl;
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

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MeasureServiceImplTestIT extends BaseTest {


    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    private BrandService brandService;
    private MeasureService measureService;
    private SubcategoryService subcategoryService;
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        measureService = new MeasureServiceImpl(measureRepository, categoryRepository,
                subcategoryRepository, brandRepository);

        brandService = new BrandServiceImpl(brandRepository);
        subcategoryService = new SubcategoryServiceImpl(subcategoryRepository);
        categoryService = new CategoryServiceImpl(categoryRepository, subcategoryRepository);

        setUpMarca();
        brand = brandService.save(brand);

        setUpSubCategoria();
        subcategory = subcategoryService.save(subcategory);

        setUpCategoria();
        category.setSubcategories(new ArrayList<>());
        category.getSubcategories().add(subcategory);
        category = categoryService.save(category);


        setUpItensTipoMedida();
        setUpMedida();
        measure.setSubcategory(subcategory);
        measure.setCategory(category);
        measure.setBrand(brand);
        measure.setItemsTypeMeasure(itemsTypeMeasure);

    }

    @Test
    public void incluir() {
        Measure measureSave = measureService.save(measure);
        assertEquals(measureSave.getItemsTypeMeasure().size(), measure.getItemsTypeMeasure().size());

        measure = measureService.findById(measureSave.getId());
        assertMarcaSubCategoriaCategoriaValor(measureSave);

    }

    @Test
    public void alterar() {
        // todo:
    }

    @Test
    public void alterarMarca() {

        measure = measureService.save(measure);

        Brand brandUpdate = new Brand();
        brandUpdate.setNome(NOME_UPDATE);
        brandUpdate.setDescricao(DESCRICAO_UPDATE);
        brandUpdate = brandService.save(brandUpdate);

        Measure toUpdate = measureService.findById(measure.getId());
        toUpdate.setCategory(category);
        toUpdate.setSubcategory(subcategory);
        toUpdate.setBrand(brandUpdate);

        Measure updated = measureService.update(measure.getId(), toUpdate);

        for (int i = 0; i < updated.getItemsTypeMeasure().size(); i++) {
            Brand brandFound = updated.getItemsTypeMeasure().get(i).getBrand();
            assertMarca(brandFound, brandUpdate);

            assertNotEquals(brandUpdate.getCodigo(), brand.getCodigo());
            assertNotEquals(brandUpdate.getNome(), brand.getNome());
            assertNotEquals(brandUpdate.getDescricao(), brand.getDescricao());
        }

    }

    @Test
    public void alterarSubCategoria() {

        measure = measureService.save(measure);

        Subcategory subcategoryUpdate = new Subcategory();
        subcategoryUpdate.setNome(NOME_UPDATE);
        subcategoryUpdate.setDescricao(DESCRICAO_UPDATE);
        subcategoryUpdate = subcategoryService.save(subcategoryUpdate);

        Measure toUpdate = measureService.findById(measure.getId());
        toUpdate.setCategory(category);
        toUpdate.setSubcategory(subcategoryUpdate);
        toUpdate.setBrand(brand);

        Measure updated = measureService.update(measure.getId(), toUpdate);

        for (int i = 0; i < updated.getItemsTypeMeasure().size(); i++) {
            Subcategory subcategoryFound = updated.getItemsTypeMeasure().get(i).getSubcategory();
            assertSubCategoria(subcategoryFound, subcategoryUpdate);

            assertNotEquals(subcategoryUpdate.getCodigo(), subcategory.getCodigo());
            assertNotEquals(subcategoryUpdate.getNome(), subcategory.getNome());
            assertNotEquals(subcategoryUpdate.getDescricao(), subcategory.getDescricao());
        }

    }

    @Test
    public void alterarCategoria() {

        measure = measureService.save(measure);

        Category categoryUpdate = new Category();
        categoryUpdate.setName(NOME_UPDATE);
        categoryUpdate.setDescription(DESCRICAO_UPDATE);
        categoryUpdate.setSubcategories(new ArrayList<>());
        categoryUpdate.getSubcategories().add(subcategory);
        categoryUpdate = categoryService.save(categoryUpdate);

        Measure toUpdate = measureService.findById(measure.getId());
        toUpdate.setCategory(categoryUpdate);
        toUpdate.setSubcategory(subcategory);
        toUpdate.setBrand(brand);

        Measure updated = measureService.update(measure.getId(), toUpdate);

        for (int i = 0; i < updated.getItemsTypeMeasure().size(); i++) {
            Category categoryFound = updated.getItemsTypeMeasure().get(i).getCategory();
            assertCategoria(categoryFound, categoryUpdate);

            assertNotEquals(categoryFound.getId(), category.getId());
            assertNotEquals(categoryFound.getName(), category.getName());
            assertNotEquals(categoryFound.getDescription(), category.getDescription());
        }
    }

    @Test
    public void excluir() {
        measure = measureService.save(measure);

        Measure delete = measureService.findById(measure.getId());
        assertNotNull(delete);

        measureService.delete(delete.getId());

        Measure found = measureService.findById(measure.getId());
        assertNull(found.getId());
        assertNull(found.getNome());
        assertNull(found.getDescricao());

    }

    @Test
    public void consultar() {
        List<Measure> measures = measureService.findAll();
        assertNotNull(measures);
    }

    @Test
    public void consultarByCodigo() {
        measure = measureService.save(measure);
        Measure measureFound = measureService.findById(measure.getId());
        assertEquals(measureFound.getId(), measure.getId());
        assertEquals(measureFound.getNome(), measure.getNome());
        assertEquals(measureFound.getDescricao(), measure.getDescricao());
        assertMarcaSubCategoriaCategoriaValor(measureFound);
    }

    @Test
    public void consultarByCategoriaSubCategoriaMarca() {
        // TODO
    }
}
