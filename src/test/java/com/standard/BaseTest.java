package com.standard;

import com.standard.domain.*;
import com.standard.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;



import static org.junit.jupiter.api.Assertions.assertEquals;


public class BaseTest {

    @Autowired
    protected WebApplicationContext wac;
    
    protected static final String API_KEY = "Api-Key";
    protected static final String API_SECRET = "Api-Secret";
    protected static final String API_KEY_VALUE = "standard";
    protected static final String API_SECRET_VALUE = "standard";


    protected static final String NOME = "nome";
    protected static final String DESCRICAO = "Descriçao";
    protected static final String NOME_UPDATE = "nomeUpdate";
    protected static final String DESCRICAO_UPDATE = "descricaoUpdate";
    protected static final String BAR_0_CODE = "0000000BAR0CODE";
    protected static final int PORCENTAGEM_DESCONTO = 15;
    protected static final int PORCENTAGEM_DESCONTO_UPDATE = 15;
    protected static final int QUANTIDADE_PRODUTOS_VENDA = 1;
    protected static final Double VALOR_UNITARIO = 10.0;

    private static final String VALOR_P = "P";
    private static final String VALOR_X = "X";
    private static final String VALOR_L = "L";
    private static final String VALOR_XL = "XL";
    private static final int QUANTIDADE = 10;
    private static final long CODIGO = 1L;
    
    // obj commons
    protected PaymentMethod paymentMethod = null;
    protected Venda venda = null;
    protected Pos pos = null;
    protected Brand brand = null;
    protected Subcategory subcategory = null;
    protected Category category = null;
    protected Provider provider = null;
    protected Measure measure = null;
    protected List<ItemsTypeMeasure> itemsTypeMeasure = null;
    protected Domain domain = null;
    protected Produto produto = null;
    protected List<ProdutoHasItensTipoMedida> produtoHasItensTipoMedida = null;
    protected ItemsTypeMeasure itenTipoMedida = null;
    protected ProdutoHasItensTipoMedida produtoHasItenTipoMedida = null;

    protected void setUpMarca() {
        brand = new Brand();
        brand.setNome(NOME);
        brand.setDescricao(DESCRICAO);
    }

    protected void setUpSubCategoria() {
        subcategory = new Subcategory();
        subcategory.setNome(NOME);
        subcategory.setDescricao(DESCRICAO);
    }

    protected void setUpCategoria() {
        category = new Category();
        category.setCodigo(1L);
        category.setNome(NOME);
        category.setDescricao(DESCRICAO);
    }

    protected void setUpFormasDePagamento(){
        paymentMethod = new PaymentMethod();
        paymentMethod.setNome(NOME);
        paymentMethod.setDescricao(DESCRICAO);
        paymentMethod.setPorcentagemDesconto(PORCENTAGEM_DESCONTO);
    }

    protected void setUpFornecedor() {
        provider = new Provider();
        provider.setCodigo(1L);
        provider.setNome(NOME);
        provider.setDescricao(DESCRICAO);
    }

    protected void setUpMedida() {
        measure = new Measure();
        // medida.setCodigo(1l);
        measure.setNome(NOME);
        measure.setDescricao(DESCRICAO);
    }

    protected void setUpItensTipoMedida() {
        itemsTypeMeasure = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            itenTipoMedida = new ItemsTypeMeasure();
            switch (i) {
                case 1:
                    itenTipoMedida.setValor(VALOR_P);
                    break;
                case 2:
                    itenTipoMedida.setValor(VALOR_L);
                    break;
                case 3:
                    itenTipoMedida.setValor(VALOR_X);
                    break;
                case 4:
                    itenTipoMedida.setValor(VALOR_XL);
                    break;
            }
            itemsTypeMeasure.add(itenTipoMedida);
        }
    }

    protected void setUpDominio() {
        domain = new Domain();
        domain.setCodigo(CODIGO);
        domain.setNome(NOME);
        domain.setDescricao(DESCRICAO);
        domain.setChecked(true);
    }

    protected void setUpProdutoHasItensTipoMedida() {
        produtoHasItensTipoMedida = new ArrayList<>();
        int j = 0;
        for (int i = 1; i < 5; i++) {
            produtoHasItenTipoMedida = new ProdutoHasItensTipoMedida();
            produtoHasItenTipoMedida.setQuantidade(QUANTIDADE);
            produtoHasItenTipoMedida.setDomains(new ArrayList<>());
            produtoHasItenTipoMedida.getDomains().add(domain);
            produtoHasItenTipoMedida.setItemsTypeMeasure(measure.getItemsTypeMeasure().get(j));
            produtoHasItensTipoMedida.add(produtoHasItenTipoMedida);
            j++;
        }
    }

    protected void setUpProduto() {
        produto = new Produto();
        produto.setCodigo(1L);
        produto.setBarCode(BAR_0_CODE);
        produto.setNome(NOME);
        produto.setStatus(StatusEnum.ATIVO);
        produto.setDescricao(DESCRICAO);
        produto.setPreco(10d);
        produto.setPrecoVenda(10d);
        produto.setPreco(10d);
        produto.setPrecoCusto(10d);
        produto.setPrecoOferta(10d);
        produto.setDesconto(10d);
        produto.setPeso(10d);
        produto.setPorcentagem(1);
        produto.setPorcentagemDesconto(1);
        // fixme: medida.setfoto
    }

    protected void assertMarcaSubCategoriaCategoriaValor(Measure measure) {
        for (int j = 0; j < measure.getItemsTypeMeasure().size(); j++) {

            Brand brandFound = measure.getItemsTypeMeasure().get(j).getBrand();
            assertMarca(brandFound, brand);

            Subcategory subcategoryFound = measure.getItemsTypeMeasure().get(j).getSubcategory();
            assertSubCategoria(subcategoryFound, subcategory);

            Category categoryFound = measure.getItemsTypeMeasure().get(j).getCategory();
            assertCategoria(categoryFound, category);

            assertEquals(measure.getItemsTypeMeasure().get(j).getValor(), measure.getItemsTypeMeasure().get(j).getValor());
        }
    }

    protected void assertMarca(Brand expected, Brand found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertCategoria(Category expected, Category found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertSubCategoria(Subcategory expected, Subcategory found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertFornecedor(Provider expected, Provider found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertDominios(Domain expected, Domain found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
        assertEquals(expected.isChecked(), found.isChecked());
    }

    private void assertProdutoHasItensTipoMedida(List<ProdutoHasItensTipoMedida> produto, List<ProdutoHasItensTipoMedida> found) {
        for (int i = 0; i < found.size(); i++) {
            assertEquals(found.get(i).getCodigo(), produto.get(i).getCodigo());
            assertEquals(found.get(i).getDomains().size(),
                    produto.get(i).getDomains().size());

            for (int j = 0; j < found.get(i).getDomains().size(); j++) {
                assertDominios(found.get(i).getDomains().get(j),
                        produto.get(i).getDomains().get(j));
            }
            assertEquals(found.get(i).getQuantidade(), produto.get(i).getQuantidade());
            assertEquals(found.get(i).getValorUnitario(), produto.get(i).getValorUnitario());
            assertEquals(found.get(i).getItemsTypeMeasure().getValor(), produto.get(i).getItemsTypeMeasure().getValor());

        }
    }

    protected void assertProduto(Produto found, Produto expected) {
        assertEquals(found.getCodigo(), expected.getCodigo());
        assertEquals(found.getBarCode(), expected.getBarCode());
        assertEquals(found.getNome(), expected.getNome());
        assertEquals(found.getStatus(), expected.getStatus());
        assertEquals(found.getDescricao(), expected.getDescricao());
        assertEquals(found.getPreco(), expected.getPreco());
        assertEquals(found.getPrecoVenda(), expected.getPrecoVenda());
        assertEquals(found.getPreco(), expected.getPreco());
        assertEquals(found.getPrecoCusto(), expected.getPrecoCusto());
        assertEquals(found.getPrecoOferta(), expected.getPrecoOferta());
        assertEquals(found.getDesconto(), expected.getDesconto());
        assertEquals(found.getPeso(), expected.getPeso());
        assertEquals(found.getPorcentagem(), expected.getPorcentagem());
        assertEquals(found.getPorcentagemDesconto(), expected.getPorcentagemDesconto());
        assertMarca(found.getBrand(), expected.getBrand());
        assertCategoria(found.getCategory(), expected.getCategory());
        assertSubCategoria(found.getSubcategory(), expected.getSubcategory());
        assertFornecedor(found.getProvider(), expected.getProvider());
        assertMarcaSubCategoriaCategoriaValor(found.getMeasure());
        assertEquals(found.getProdutoHasItensTipoMedida().size(), expected.getProdutoHasItensTipoMedida().size());
        assertProdutoHasItensTipoMedida(found.getProdutoHasItensTipoMedida(), expected.getProdutoHasItensTipoMedida());
    }

    protected void asserItensTipoMedida(ItemsTypeMeasure found, ItemsTypeMeasure expected) {
        assertEquals(found.getId(), expected.getId());
        assertEquals(found.getValor(), expected.getValor());
        assertEquals(found.getMeasure(), expected.getMeasure());
        assertEquals(found.getBrand(), expected.getBrand());
        assertEquals(found.getCategory(), expected.getCategory());
        assertEquals(found.getSubcategory(), expected.getSubcategory());
    }
    
    

}
