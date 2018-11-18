package com.standard.service.medida;

import com.standard.BaseTest;
import com.standard.domain.Categoria;
import com.standard.domain.Marca;
import com.standard.domain.Medida;
import com.standard.domain.SubCategoria;
import com.standard.repository.CategoriaRepository;
import com.standard.repository.MarcaRepository;
import com.standard.repository.MedidaRepository;
import com.standard.repository.SubCategoriaRepository;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.categoria.CategoriaServiceImpl;
import com.standard.service.marca.MarcaService;
import com.standard.service.marca.MarcaServiceImpl;
import com.standard.service.subCategoria.SubCategoriaService;
import com.standard.service.subCategoria.SubCategoriaServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedidaServiceImplTestIT extends BaseTest {


    @Autowired
    private MedidaRepository medidaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    private MarcaService marcaService;
    private MedidaService medidaService;
    private SubCategoriaService subCategoriaService;
    private CategoriaService categoriaService;

    @Before
    public void setUp() {
        medidaService = new MedidaServiceImpl(medidaRepository, categoriaRepository,
                                              subCategoriaRepository, marcaRepository);

        marcaService = new MarcaServiceImpl(marcaRepository);
        subCategoriaService = new SubCategoriaServiceImpl(subCategoriaRepository);
        categoriaService = new CategoriaServiceImpl(categoriaRepository, subCategoriaRepository);

        setUpMarca();
        marca = marcaService.incluir(marca);

        setUpSubCategoria();
        subCategoria = subCategoriaService.incluir(subCategoria);

        setUpCategoria();
        categoria.setSubCategorias(new ArrayList<>());
        categoria.getSubCategorias().add(subCategoria);
        categoria = categoriaService.incluir(categoria);


        setUpItensTipoMedida();
        setUpMedida();
        medida.setSubCategoria(subCategoria);
        medida.setCategoria(categoria);
        medida.setMarca(marca);
        medida.setItensTipoMedida(itensTipoMedida);

    }

    @Test
    public void incluir() {
        Medida medidaSave = medidaService.incluir(medida);
        Assert.assertEquals(medidaSave.getItensTipoMedida().size(), medida.getItensTipoMedida().size());

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
        toUpdate.setSubCategoria(subCategoria);
        toUpdate.setMarca(marcaUpdate);

        Medida updated = medidaService.alterar(medida.getCodigo(), toUpdate);

        for (int i = 0; i < updated.getItensTipoMedida().size(); i++) {
            Marca marcaFound = updated.getItensTipoMedida().get(i).getMarca();
            assertMarca(marcaFound, marcaUpdate);

            Assert.assertNotEquals(marcaUpdate.getCodigo(), marca.getCodigo());
            Assert.assertNotEquals(marcaUpdate.getNome(), marca.getNome());
            Assert.assertNotEquals(marcaUpdate.getDescricao(), marca.getDescricao());
        }

    }

    @Test
    public void alterarSubCategoria() {

        medida = medidaService.incluir(medida);

        SubCategoria subCategoriaUpdate = new SubCategoria();
        subCategoriaUpdate.setNome(NOME_UPDATE);
        subCategoriaUpdate.setDescricao(DESCRICAO_UPDATE);
        subCategoriaUpdate = subCategoriaService.incluir(subCategoriaUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(medida.getCodigo());
        toUpdate.setCategoria(categoria);
        toUpdate.setSubCategoria(subCategoriaUpdate);
        toUpdate.setMarca(marca);

        Medida updated = medidaService.alterar(medida.getCodigo(), toUpdate);

        for (int i = 0; i < updated.getItensTipoMedida().size(); i++) {
            SubCategoria subCategoriaFound = updated.getItensTipoMedida().get(i).getSubCategoria();
            assertSubCategoria(subCategoriaFound, subCategoriaUpdate);

            Assert.assertNotEquals(subCategoriaUpdate.getCodigo(), subCategoria.getCodigo());
            Assert.assertNotEquals(subCategoriaUpdate.getNome(), subCategoria.getNome());
            Assert.assertNotEquals(subCategoriaUpdate.getDescricao(), subCategoria.getDescricao());
        }

    }

    @Test
    public void alterarCategoria() {

        medida = medidaService.incluir(medida);

        Categoria categoriaUpdate = new Categoria();
        categoriaUpdate.setNome(NOME_UPDATE);
        categoriaUpdate.setDescricao(DESCRICAO_UPDATE);
        categoriaUpdate.setSubCategorias(new ArrayList<>());
        categoriaUpdate.getSubCategorias().add(subCategoria);
        categoriaUpdate = categoriaService.incluir(categoriaUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(medida.getCodigo());
        toUpdate.setCategoria(categoriaUpdate);
        toUpdate.setSubCategoria(subCategoria);
        toUpdate.setMarca(marca);

        Medida updated = medidaService.alterar(medida.getCodigo(), toUpdate);

        for (int i = 0; i < updated.getItensTipoMedida().size(); i++) {
            Categoria categoriaFound = updated.getItensTipoMedida().get(i).getCategoria();
            assertCategoria(categoriaFound, categoriaUpdate);

            Assert.assertNotEquals(categoriaFound.getCodigo(), categoria.getCodigo());
            Assert.assertNotEquals(categoriaFound.getNome(), categoria.getNome());
            Assert.assertNotEquals(categoriaFound.getDescricao(), categoria.getDescricao());
        }
    }

    @Test
    public void excluir() {
        medida = medidaService.incluir(medida);

        Medida delete = medidaService.consultarByCodigo(medida.getCodigo());
        Assert.assertNotNull(delete);

        medidaService.excluir(delete.getCodigo());

        Medida found = medidaService.consultarByCodigo(medida.getCodigo());
        Assert.assertNull(found.getCodigo());
        Assert.assertNull(found.getNome());
        Assert.assertNull(found.getDescricao());

    }

    @Test
    public void consultar() {
        List<Medida> medidas = medidaService.consultar();
        Assert.assertNotNull(medidas);
    }

    @Test
    public void consultarByCodigo() {
        medida = medidaService.incluir(medida);
        Medida medidaFound = medidaService.consultarByCodigo(medida.getCodigo());
        Assert.assertEquals(medidaFound.getCodigo(), medida.getCodigo());
        Assert.assertEquals(medidaFound.getNome(), medida.getNome());
        Assert.assertEquals(medidaFound.getDescricao() , medida.getDescricao());
        assertMarcaSubCategoriaCategoriaValor(medidaFound);
    }

    @Test
    public void consultarByCategoriaSubCategoriaMarca() {
        // TODO
    }
}