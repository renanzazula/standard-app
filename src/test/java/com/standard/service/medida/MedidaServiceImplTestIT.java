package com.standard.service.medida;

import com.standard.BaseTest;
import com.standard.domain.Categoria;
import com.standard.domain.Marca;
import com.standard.domain.Medida;
import com.standard.domain.Subcategoria;
import com.standard.repository.CategoriaRepository;
import com.standard.repository.MarcaRepository;
import com.standard.repository.MedidaRepository;
import com.standard.repository.SubcategoriaRepository;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.categoria.CategoriaServiceImpl;
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
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    private MarcaService marcaService;
    private MedidaService medidaService;
    private SubcategoriaService subcategoriaService;
    private CategoriaService categoriaService;

    @BeforeEach
    public void setUp() {
        medidaService = new MedidaServiceImpl(medidaRepository, categoriaRepository,
                subcategoriaRepository, marcaRepository);

        marcaService = new MarcaServiceImpl(marcaRepository);
        subcategoriaService = new SubcategoriaServiceImpl(subcategoriaRepository);
        categoriaService = new CategoriaServiceImpl(categoriaRepository, subcategoriaRepository);

        setUpMarca();
        marca = marcaService.incluir(marca);

        setUpSubCategoria();
        subcategoria = subcategoriaService.incluir(subcategoria);

        setUpCategoria();
        categoria.setSubcategorias(new ArrayList<>());
        categoria.getSubcategorias().add(subcategoria);
        categoria = categoriaService.incluir(categoria);


        setUpItensTipoMedida();
        setUpMedida();
        medida.setSubcategoria(subcategoria);
        medida.setCategoria(categoria);
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
        toUpdate.setCategoria(categoria);
        toUpdate.setSubcategoria(subcategoria);
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

        Subcategoria subcategoriaUpdate = new Subcategoria();
        subcategoriaUpdate.setNome(NOME_UPDATE);
        subcategoriaUpdate.setDescricao(DESCRICAO_UPDATE);
        subcategoriaUpdate = subcategoriaService.incluir(subcategoriaUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(medida.getCodigo());
        toUpdate.setCategoria(categoria);
        toUpdate.setSubcategoria(subcategoriaUpdate);
        toUpdate.setMarca(marca);

        Medida updated = medidaService.alterar(medida.getCodigo(), toUpdate);

        for (int i = 0; i < updated.getItensTipoMedida().size(); i++) {
            Subcategoria subcategoriaFound = updated.getItensTipoMedida().get(i).getSubcategoria();
            assertSubCategoria(subcategoriaFound, subcategoriaUpdate);

            assertNotEquals(subcategoriaUpdate.getCodigo(), subcategoria.getCodigo());
            assertNotEquals(subcategoriaUpdate.getNome(), subcategoria.getNome());
            assertNotEquals(subcategoriaUpdate.getDescricao(), subcategoria.getDescricao());
        }

    }

    @Test
    public void alterarCategoria() {

        medida = medidaService.incluir(medida);

        Categoria categoriaUpdate = new Categoria();
        categoriaUpdate.setNome(NOME_UPDATE);
        categoriaUpdate.setDescricao(DESCRICAO_UPDATE);
        categoriaUpdate.setSubcategorias(new ArrayList<>());
        categoriaUpdate.getSubcategorias().add(subcategoria);
        categoriaUpdate = categoriaService.incluir(categoriaUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(medida.getCodigo());
        toUpdate.setCategoria(categoriaUpdate);
        toUpdate.setSubcategoria(subcategoria);
        toUpdate.setMarca(marca);

        Medida updated = medidaService.alterar(medida.getCodigo(), toUpdate);

        for (int i = 0; i < updated.getItensTipoMedida().size(); i++) {
            Categoria categoriaFound = updated.getItensTipoMedida().get(i).getCategoria();
            assertCategoria(categoriaFound, categoriaUpdate);

            assertNotEquals(categoriaFound.getCodigo(), categoria.getCodigo());
            assertNotEquals(categoriaFound.getNome(), categoria.getNome());
            assertNotEquals(categoriaFound.getDescricao(), categoria.getDescricao());
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