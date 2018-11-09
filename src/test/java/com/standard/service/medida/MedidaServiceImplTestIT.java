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

    public static final String VALOR_P  = "P";
    public static final String VALOR_X  = "X";
    public static final String VALOR_L  = "L";
    public static final String VALOR_XL = "XL";

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
    private ItensTipoMedida  itenTipoMedida;
    private Medida medida;
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

        subCategoria =  new SubCategoria();
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
            switch (i){
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
        obj = medidaService.incluir(obj);
        // Todo Asserts

    }

    @Test
    public void alterar() {
    }

    @Test
    public void excluir() {
    }

    @Test
    public void consultar() {
    }

    @Test
    public void consultarByCodigo() {
    }

    @Test
    public void consultarByCategoriaSubCategoriaMarca() {
    }
}