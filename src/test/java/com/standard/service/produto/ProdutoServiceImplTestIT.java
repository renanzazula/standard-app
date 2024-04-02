package com.standard.service.produto;

import com.standard.BaseTest;
import com.standard.domain.Provider;
import com.standard.domain.Brand;
import com.standard.domain.Produto;
import com.standard.enums.StatusEnum;
import com.standard.repository.*;
import com.standard.service.categoria.CategoryService;
import com.standard.service.categoria.CategoryServiceImpl;
import com.standard.service.dominio.DomainService;
import com.standard.service.dominio.DomainServiceImpl;
import com.standard.service.fornecedor.ProviderService;
import com.standard.service.fornecedor.ProviderServiceImpl;
import com.standard.service.marca.BrandService;
import com.standard.service.marca.BrandServiceImpl;
import com.standard.service.medida.MeasureService;
import com.standard.service.medida.MeasureServiceImpl;
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
public class ProdutoServiceImplTestIT extends BaseTest {


    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItensTipoMedidaRepository itensTipoMedidaRepository;

    private BrandService brandService;
    private ProviderService providerService;
    private ProdutoService produtoService;
    private SubcategoryService subcategoryService;
    private CategoryService categoryService;
    private DomainService domainService;
    private MeasureService measureService;



    @BeforeEach
    public void setUp() {

        brandService = new BrandServiceImpl(brandRepository);
        subcategoryService = new SubcategoryServiceImpl(subcategoryRepository);
        categoryService = new CategoryServiceImpl(categoryRepository, subcategoryRepository);
        providerService = new ProviderServiceImpl(providerRepository);
        domainService = new DomainServiceImpl(domainRepository);
        measureService = new MeasureServiceImpl(measureRepository, categoryRepository, subcategoryRepository,
                brandRepository);
        produtoService = new ProdutoServiceImpl(produtoRepository, measureRepository,
                domainRepository, providerRepository,
                categoryRepository, subcategoryRepository,
                brandRepository, itensTipoMedidaRepository);

        // requeridos
        setUpMarca();
        brand = brandService.save(brand);

        setUpFornecedor();
        provider = providerService.save(provider);

        setUpSubCategoria();
        subcategory = subcategoryService.save(subcategory);

        setUpCategoria();
        category.setSubcategories(new ArrayList<>());
        category.getSubcategories().add(subcategory);
        category = categoryService.save(category);

        setUpDominio();
        domain = domainService.save(domain);

        setUpItensTipoMedida();
        setUpMedida();
        measure.setSubcategory(subcategory);
        measure.setCategory(category);
        measure.setBrand(brand);
        measure.setItemsTypeMeasure(itemsTypeMeasure);
        measure = measureService.save(measure);

        //quantadade, dominio e item Medida
        setUpProdutoHasItensTipoMedida();

        // campos comuns
        setUpProduto();

        produto.setBrand(brand);
        produto.setProvider(provider);
        produto.setCategory(category);
        produto.setMeasure(measure);
        produto.setSubcategory(subcategory);
        produto.setProdutoHasItensTipoMedida(produtoHasItensTipoMedida);


    }

    @Test
    public void incluir() {
        Produto produtoSave = produtoService.incluir(produto);
        Produto found = produtoService.consultarByCodigo(produtoSave.getCodigo());
        assertProduto(found, produtoSave);
    }

    @Test
    public void alterar() {
        produto = produtoService.incluir(produto);

        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        found.setBarCode(BAR_0_CODE + "_update");
        found.setNome(NOME + "_update");
        found.setStatus(StatusEnum.INATIVO);
        found.setDescricao(DESCRICAO + "_update");
        found.setPreco(15d);
        found.setPrecoVenda(15d);
        found.setPreco(15d);
        found.setPrecoCusto(15d);
        found.setPrecoOferta(15d);
        found.setDesconto(15d);
        found.setPeso(15d);
        found.setPorcentagem(2);
        found.setPorcentagemDesconto(2);

        Produto updated = produtoService.alterar(produto.getCodigo(), found);
        assertEquals(found.getCodigo(), updated.getCodigo());
        assertEquals(found.getBarCode(), updated.getBarCode());
        assertEquals(found.getNome(), updated.getNome());
        assertEquals(found.getStatus(), updated.getStatus());
        assertEquals(found.getDescricao(), updated.getDescricao());
        assertEquals(found.getPreco(), updated.getPreco());
        assertEquals(found.getPrecoVenda(), updated.getPrecoVenda());
        assertEquals(found.getPreco(), updated.getPreco());
        assertEquals(found.getPrecoCusto(), updated.getPrecoCusto());
        assertEquals(found.getPrecoOferta(), updated.getPrecoOferta());
        assertEquals(found.getDesconto(), updated.getDesconto());
        assertEquals(found.getPeso(), updated.getPeso());
        assertEquals(found.getPorcentagem(), updated.getPorcentagem());
        assertEquals(found.getPorcentagemDesconto(), updated.getPorcentagemDesconto());

        assertMarca(found.getBrand(), updated.getBrand());
        assertCategoria(found.getCategory(), updated.getCategory());
        assertSubCategoria(found.getSubcategory(), updated.getSubcategory());
        assertFornecedor(found.getProvider(), updated.getProvider());
        assertMarcaSubCategoriaCategoriaValor(found.getMeasure());
        assertEquals(found.getProdutoHasItensTipoMedida().size(), updated.getProdutoHasItensTipoMedida().size());

        for (int i = 0; i < found.getProdutoHasItensTipoMedida().size(); i++) {

            assertEquals(found.getProdutoHasItensTipoMedida().get(i).getDomains().size(),
                    updated.getProdutoHasItensTipoMedida().get(i).getDomains().size());

            for (int j = 0; j < found.getProdutoHasItensTipoMedida().get(i).getDomains().size(); j++) {
                assertDominios(found.getProdutoHasItensTipoMedida().get(i).getDomains().get(j),
                        updated.getProdutoHasItensTipoMedida().get(i).getDomains().get(j));
            }

            assertEquals(found.getProdutoHasItensTipoMedida().get(i).getQuantidade(),
                    updated.getProdutoHasItensTipoMedida().get(i).getQuantidade());

            assertEquals(found.getProdutoHasItensTipoMedida().get(i).getValorUnitario(),
                    updated.getProdutoHasItensTipoMedida().get(i).getValorUnitario());

            assertEquals(found.getProdutoHasItensTipoMedida().get(0).getItemsTypeMeasure().getValor(),
                    updated.getProdutoHasItensTipoMedida().get(0).getItemsTypeMeasure().getValor());

        }

    }

    @Test
    public void alterar_produto_Marca() {

        Brand brandToUpdate = new Brand();
        brandToUpdate.setNome(NOME + "_update");
        brandToUpdate.setDescricao(DESCRICAO + "_update");
        brandToUpdate = brandService.save(brandToUpdate);

        produto = produtoService.incluir(produto);

        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        found.setBrand(brandToUpdate);

        Produto updated = produtoService.alterar(produto.getCodigo(), found);

        assertMarca(updated.getBrand(), brandToUpdate);

        assertNotEquals(updated.getBrand().getCodigo(), brand.getCodigo());
        assertNotEquals(updated.getBrand().getNome(), brand.getNome());
        assertNotEquals(updated.getBrand().getDescricao(), brand.getDescricao());

    }

    @Test
    public void alterar_produto_Categoria() {
        // TODO:
    }

    @Test
    public void alterar_produto_SubCategoria() {
        // TODO:
    }

    @Test
    public void alterar_produto_Fornecedor() {

        Provider providerToUpdate = new Provider();
        providerToUpdate.setNome(NOME + "_update");
        providerToUpdate.setDescricao(DESCRICAO + "_update");
        providerToUpdate = providerService.save(providerToUpdate);

        produto = produtoService.incluir(produto);

        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        found.setProvider(providerToUpdate);

        Produto updated = produtoService.alterar(produto.getCodigo(), found);

        assertFornecedor(updated.getProvider(), providerToUpdate);

        assertNotEquals(updated.getProvider().getCodigo(), provider.getCodigo());
        assertNotEquals(updated.getProvider().getNome(), provider.getNome());
        assertNotEquals(updated.getProvider().getDescricao(), provider.getDescricao());

    }

    @Test
    public void excluir() {
        produto = produtoService.incluir(produto);
        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        assertNotNull(found);
        produtoService.excluir(found.getCodigo());
    }


    @Test
    public void consultarByCodigo() {
        produto = produtoService.incluir(produto);
        Produto found = produtoService.consultarByCodigo(produto.getCodigo());
        assertNotNull(found);
        assertEquals(found.getCodigo(), produto.getCodigo());
    }

    @Test
    public void consultarByBarCode() {
        produto = produtoService.incluir(produto);
        Produto found = produtoService.consultarByBarCode(produto.getBarCode());
        assertNotNull(found);
        assertEquals(found.getCodigo(), produto.getCodigo());
    }

    @Test
    public void consultar() {
        produto = produtoService.incluir(produto);
        List<Produto> produtos = produtoService.consultar();
        assertNotNull(produtos);
    }
}
