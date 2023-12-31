package com.standard;

import com.standard.domain.*;
import com.standard.enums.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
    protected FormasDePagamento formasDePagamento = null;
    protected Venda venda = null;
    protected Caixa caixa = null;
    protected Marca marca = null;
    protected Subcategoria subcategoria = null;
    protected Categoria categoria = null;
    protected Fornecedor fornecedor = null;
    protected Medida medida = null;
    protected List<ItensTipoMedida> itensTipoMedida = null;
    protected Dominio dominio = null;
    protected Produto produto = null;
    protected List<ProdutoHasItensTipoMedida> produtoHasItensTipoMedida = null;
    protected ItensTipoMedida itenTipoMedida = null;
    protected ProdutoHasItensTipoMedida produtoHasItenTipoMedida = null;

    protected void setUpMarca() {
        marca = new Marca();
        marca.setNome(NOME);
        marca.setDescricao(DESCRICAO);
    }

    protected void setUpSubCategoria() {
        subcategoria = new Subcategoria();
        subcategoria.setNome(NOME);
        subcategoria.setDescricao(DESCRICAO);
    }

    protected void setUpCategoria() {
        categoria = new Categoria();
        categoria.setCodigo(1L);
        categoria.setNome(NOME);
        categoria.setDescricao(DESCRICAO);
    }

    protected void setUpFormasDePagamento(){
        formasDePagamento = new FormasDePagamento();
        formasDePagamento.setNome(NOME);
        formasDePagamento.setDescricao(DESCRICAO);
        formasDePagamento.setPorcentagemDesconto(PORCENTAGEM_DESCONTO);
    }

    protected void setUpFornecedor() {
        fornecedor = new Fornecedor();
        fornecedor.setCodigo(1L);
        fornecedor.setNome(NOME);
        fornecedor.setDescricao(DESCRICAO);
    }

    protected void setUpMedida() {
        medida = new Medida();
        // medida.setCodigo(1l);
        medida.setNome(NOME);
        medida.setDescricao(DESCRICAO);
    }

    protected void setUpItensTipoMedida() {
        itensTipoMedida = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            itenTipoMedida = new ItensTipoMedida();
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
            itensTipoMedida.add(itenTipoMedida);
        }
    }

    protected void setUpDominio() {
        dominio = new Dominio();
        dominio.setCodigo(CODIGO);
        dominio.setNome(NOME);
        dominio.setDescricao(DESCRICAO);
        dominio.setChecked(true);
    }

    protected void setUpProdutoHasItensTipoMedida() {
        produtoHasItensTipoMedida = new ArrayList<>();
        int j = 0;
        for (int i = 1; i < 5; i++) {
            produtoHasItenTipoMedida = new ProdutoHasItensTipoMedida();
            produtoHasItenTipoMedida.setQuantidade(QUANTIDADE);
            produtoHasItenTipoMedida.setDominios(new ArrayList<>());
            produtoHasItenTipoMedida.getDominios().add(dominio);
            produtoHasItenTipoMedida.setItensTipoMedida(medida.getItensTipoMedida().get(j));
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

    protected void assertMarcaSubCategoriaCategoriaValor(Medida medida) {
        for (int j = 0; j < medida.getItensTipoMedida().size(); j++) {

            Marca marcaFound = medida.getItensTipoMedida().get(j).getMarca();
            assertMarca(marcaFound, marca);

            Subcategoria subcategoriaFound = medida.getItensTipoMedida().get(j).getSubcategoria();
            assertSubCategoria(subcategoriaFound, subcategoria);

            Categoria categoriaFound = medida.getItensTipoMedida().get(j).getCategoria();
            assertCategoria(categoriaFound, categoria);

            assertEquals(medida.getItensTipoMedida().get(j).getValor(), medida.getItensTipoMedida().get(j).getValor());
        }
    }

    protected void assertMarca(Marca expected, Marca found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertCategoria(Categoria expected, Categoria found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertSubCategoria(Subcategoria expected, Subcategoria found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertFornecedor(Fornecedor expected, Fornecedor found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertDominios(Dominio expected, Dominio found) {
        assertEquals(expected.getCodigo(), found.getCodigo());
        assertEquals(expected.getNome(), found.getNome());
        assertEquals(expected.getDescricao(), found.getDescricao());
        assertEquals(expected.isChecked(), found.isChecked());
    }

    private void assertProdutoHasItensTipoMedida(List<ProdutoHasItensTipoMedida> produto, List<ProdutoHasItensTipoMedida> found) {
        for (int i = 0; i < found.size(); i++) {
            assertEquals(found.get(i).getCodigo(), produto.get(i).getCodigo());
            assertEquals(found.get(i).getDominios().size(),
                    produto.get(i).getDominios().size());

            for (int j = 0; j < found.get(i).getDominios().size(); j++) {
                assertDominios(found.get(i).getDominios().get(j),
                        produto.get(i).getDominios().get(j));
            }
            assertEquals(found.get(i).getQuantidade(), produto.get(i).getQuantidade());
            assertEquals(found.get(i).getValorUnitario(), produto.get(i).getValorUnitario());
            assertEquals(found.get(i).getItensTipoMedida().getValor(), produto.get(i).getItensTipoMedida().getValor());

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
        assertMarca(found.getMarca(), expected.getMarca());
        assertCategoria(found.getCategoria(), expected.getCategoria());
        assertSubCategoria(found.getSubcategoria(), expected.getSubcategoria());
        assertFornecedor(found.getFornecedor(), expected.getFornecedor());
        assertMarcaSubCategoriaCategoriaValor(found.getMedida());
        assertEquals(found.getProdutoHasItensTipoMedida().size(), expected.getProdutoHasItensTipoMedida().size());
        assertProdutoHasItensTipoMedida(found.getProdutoHasItensTipoMedida(), expected.getProdutoHasItensTipoMedida());
    }

    protected void asserItensTipoMedida(ItensTipoMedida found, ItensTipoMedida expected) {
        assertEquals(found.getCodigo(), expected.getCodigo());
        assertEquals(found.getValor(), expected.getValor());
        assertEquals(found.getMedida(), expected.getMedida());
        assertEquals(found.getMarca(), expected.getMarca());
        assertEquals(found.getCategoria(), expected.getCategoria());
        assertEquals(found.getSubcategoria(), expected.getSubcategoria());
    }
    
    

}
