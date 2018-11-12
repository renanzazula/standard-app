package com.standard.service.medida;

import com.standard.BaseTest;
import com.standard.domain.*;
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
@DataJpaTest(showSql = true)
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


    private Medida obj;
    private List<ItensTipoMedida> itensTipoMedida;
    private ItensTipoMedida itenTipoMedida;

    private Marca marca;
    private Categoria categoria;
    private SubCategoria subCategoria;


    @Before
    public void setUp() {
        medidaService = new MedidaServiceImpl(medidaRepository, categoriaRepository,
                subCategoriaRepository, marcaRepository);

        marcaService = new MarcaServiceImpl(marcaRepository);
        subCategoriaService = new SubCategoriaServiceImpl(subCategoriaRepository);
        categoriaService = new CategoriaServiceImpl(categoriaRepository, subCategoriaRepository);

        marca = new Marca();
        marca.setNome(NOME);
        marca.setDescricao(DESCRICAO);
        marca = marcaService.incluir(marca);

        subCategoria = new SubCategoria();
        subCategoria.setNome(NOME);
        subCategoria.setDescricao(DESCRICAO);
        subCategoria = subCategoriaService.incluir(subCategoria);

        categoria = new Categoria();
        categoria.setNome(NOME);
        categoria.setDescricao(DESCRICAO);
        categoria.setSubCategorias(new ArrayList<>());
        categoria.getSubCategorias().add(subCategoria);
        categoria = categoriaService.incluir(categoria);

        itensTipoMedida = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            itenTipoMedida = new ItensTipoMedida();

            switch (i) {
                case 0:
                    itenTipoMedida.setValor(VALOR_P);
                    break;
                case 1:
                    itenTipoMedida.setValor(VALOR_L);
                    break;
                case 2:
                    itenTipoMedida.setValor(VALOR_X);
                    break;
                case 3:
                    itenTipoMedida.setValor(VALOR_XL);
                    break;
            }
            itensTipoMedida.add(itenTipoMedida);
        }

        obj = new Medida();
        obj.setNome(NOME);
        obj.setDescricao(DESCRICAO);
        obj.setSubCategoria(subCategoria);
        obj.setCategoria(categoria);
        obj.setMarca(marca);
        obj.setItensTipoMedida(itensTipoMedida);

    }

    @Test
    public void incluir() {
        Medida medidaSave = medidaService.incluir(obj);
        Assert.assertEquals(medidaSave.getItensTipoMedida().size(), obj.getItensTipoMedida().size());

        obj = medidaService.consultarByCodigo(medidaSave.getCodigo());
        assertMarcaSubCategoriaCategoria(medidaSave);
    }

    @Test
    public void alterar() {
    }

    @Test
    public void alterarMarca() {

        obj = medidaService.incluir(obj);


        Marca marcaUpdate = new Marca();
        marcaUpdate.setNome(NOME_UPDATE);
        marcaUpdate.setDescricao(DESCRICAO_UPDATE);
        marcaUpdate = marcaService.incluir(marcaUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(obj.getCodigo());
        toUpdate.setCategoria(categoria);
        toUpdate.setSubCategoria(subCategoria);
        toUpdate.setMarca(marcaUpdate);

        Medida updated = medidaService.alterar(obj.getCodigo(), toUpdate);

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

        obj = medidaService.incluir(obj);

        SubCategoria subCategoriaUpdate = new SubCategoria();
        subCategoriaUpdate.setNome(NOME_UPDATE);
        subCategoriaUpdate.setDescricao(DESCRICAO_UPDATE);
        subCategoriaUpdate = subCategoriaService.incluir(subCategoriaUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(obj.getCodigo());
        toUpdate.setCategoria(categoria);
        toUpdate.setSubCategoria(subCategoriaUpdate);
        toUpdate.setMarca(marca);

        Medida updated = medidaService.alterar(obj.getCodigo(), toUpdate);

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

        obj = medidaService.incluir(obj);

        Categoria categoriaUpdate = new Categoria();
        categoriaUpdate.setNome(NOME_UPDATE);
        categoriaUpdate.setDescricao(DESCRICAO_UPDATE);
        categoriaUpdate.setSubCategorias(new ArrayList<>());
        categoriaUpdate.getSubCategorias().add(subCategoria);
        categoriaUpdate = categoriaService.incluir(categoriaUpdate);

        Medida toUpdate = medidaService.consultarByCodigo(obj.getCodigo());
        toUpdate.setCategoria(categoriaUpdate);
        toUpdate.setSubCategoria(subCategoria);
        toUpdate.setMarca(marca);

        Medida updated = medidaService.alterar(obj.getCodigo(), toUpdate);

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
        obj = medidaService.incluir(obj);

        Medida delete = medidaService.consultarByCodigo(obj.getCodigo());
        Assert.assertNotNull(delete);

        medidaService.excluir(delete.getCodigo());

        Medida found = medidaService.consultarByCodigo(obj.getCodigo());
        Assert.assertNull(found.getCodigo());
        Assert.assertNull(found.getNome());
        Assert.assertNull(found.getDescricao());

    }

    @Test
    public void consultar() {
        List<Medida> medidas = medidaService.consultar();
        Assert.assertNotNull(medidas);
    }

    private void assertMarcaSubCategoriaCategoria(Medida medida) {
        for (int j = 0; j < medida.getItensTipoMedida().size(); j++) {

            Marca marcaFound = medida.getItensTipoMedida().get(j).getMarca();
            assertMarca(marcaFound, marca);

            SubCategoria subCategoriaFound = medida.getItensTipoMedida().get(j).getSubCategoria();
            assertSubCategoria(subCategoriaFound, subCategoria);

            Categoria categoriaFound = medida.getItensTipoMedida().get(j).getCategoria();
            assertCategoria(categoriaFound, categoria);

            Assert.assertEquals(medida.getItensTipoMedida().get(j).getValor(), obj.getItensTipoMedida().get(j).getValor());
        }
    }

    @Test
    public void consultarByCodigo() {
        obj = medidaService.incluir(obj);
        Medida medidaFound = medidaService.consultarByCodigo(obj.getCodigo());
        Assert.assertEquals(medidaFound.getCodigo(), obj.getCodigo());
        Assert.assertEquals(medidaFound.getNome(), obj.getNome());
        Assert.assertEquals(medidaFound.getDescricao() , obj.getDescricao());
        assertMarcaSubCategoriaCategoria(medidaFound);
    }

    @Test
    public void consultarByCategoriaSubCategoriaMarca() {
    }
}