package com.standard;

import com.standard.domain.*;
import com.standard.enums.StatusEnum;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected static final String NOME = "nome";
    protected static final String DESCRICAO = "Descriçao";
    protected static final String NOME_UPDATE = "nomeUpdate";
    protected static final String DESCRICAO_UPDATE = "descricaoUpdate";
    protected static final String BAR_0_CODE = "0000000BAR0CODE";
    protected static final int PORCENTAGEM_DESCONTO = 15;
    protected static final int PORCENTAGEM_DESCONTO_UPDATE = 15;

    protected static final String VALOR_P = "P";
    protected static final String VALOR_X = "X";
    protected static final String VALOR_L = "L";
    protected static final String VALOR_XL = "XL";
    public static final int QUANTIDADE = 10;
    public static final long CODIGO = 1l;

    // obj commons
    protected Marca marca = null;
    protected SubCategoria subCategoria = null;
    protected Categoria categoria = null;
    protected Fornecedor fornecedor = null;
    protected Medida medida = null;
    protected List<ItensTipoMedida> itensTipoMedida = null;
    protected ItensTipoMedida itenTipoMedida = null;
    protected Dominio dominio = null;

    protected Produto produto = null;
    protected List<ProdutoHasItensTipoMedida> produtoHasItensTipoMedida = null;
    protected ProdutoHasItensTipoMedida produtoHasItenTipoMedida = null;

    public void setUpMarca() {
        marca = new Marca();
        marca.setNome(NOME);
        marca.setDescricao(DESCRICAO);
    }

    public void setUpSubCategoria() {
        subCategoria = new SubCategoria();
        subCategoria.setNome(NOME);
        subCategoria.setDescricao(DESCRICAO);
    }

    public void setUpCategoria() {
        categoria = new Categoria();
        categoria.setCodigo(1l);
        categoria.setNome(NOME);
        categoria.setDescricao(DESCRICAO);
    }

    public void setUpFornecedor() {
        fornecedor = new Fornecedor();
        fornecedor.setCodigo(1l);
        fornecedor.setNome(NOME);
        fornecedor.setDescricao(DESCRICAO);
    }

    public void setUpMedida() {
        medida = new Medida();
        medida.setCodigo(1l);
        medida.setNome(NOME);
        medida.setDescricao(DESCRICAO);
    }

    public void setUpItensTipoMedida() {
        itensTipoMedida = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            itenTipoMedida = new ItensTipoMedida();
            itenTipoMedida.setCodigo(new Long(i));
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
    }

    public void setUpDominio() {
        dominio = new Dominio();
        dominio.setCodigo(CODIGO);
        dominio.setNome(NOME);
        dominio.setDescricao(DESCRICAO);
        dominio.setChecked(true);
    }

    public void setUpProdutoHasItensTipoMedida() {
        produtoHasItensTipoMedida = new ArrayList<>();
        int j = 0;
        for (int i = 1; i < 5; i++) {
            produtoHasItenTipoMedida = new ProdutoHasItensTipoMedida();
            produtoHasItenTipoMedida.setQuantidade(QUANTIDADE);
            produtoHasItenTipoMedida.setDominios(new ArrayList<>());
            produtoHasItenTipoMedida.getDominios().add(dominio);
            produtoHasItenTipoMedida.setItensTipoMedida(itensTipoMedida.get(j));
            produtoHasItensTipoMedida.add(produtoHasItenTipoMedida);
            j++;
        }
    }

    public void setUpProduto() {
        produto = new Produto();
        produto.setCodigo(1l);
        produto.setBarCode(BAR_0_CODE);
        produto.setNome(NOME);
        produto.setStatus(StatusEnum.Ativo);
        produto.setDescricao(DESCRICAO);
        produto.setPreco(new Double(10));
        produto.setPrecoVenda(new Double(10));
        produto.setPreco(new Double(10));
        produto.setPrecoCusto(new Double(10));
        produto.setPrecoOferta(new Double(10));
        produto.setDesconto(new Double(10));
        produto.setPeso(new Double(10));
        produto.setPorcentagem(1);
        produto.setPorcentagemDesconto(1);
        // fixme: medida.setfoto
    }

    protected void assertMarca(Marca expected, Marca found) {
        Assert.assertEquals(expected.getCodigo(), found.getCodigo());
        Assert.assertEquals(expected.getNome(), found.getNome());
        Assert.assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertCategoria(Categoria expected, Categoria found) {
        Assert.assertEquals(expected.getCodigo(), found.getCodigo());
        Assert.assertEquals(expected.getNome(), found.getNome());
        Assert.assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertSubCategoria(SubCategoria expected, SubCategoria found) {
        Assert.assertEquals(expected.getCodigo(), found.getCodigo());
        Assert.assertEquals(expected.getNome(), found.getNome());
        Assert.assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertFornecedor(Fornecedor expected, Fornecedor found) {
        Assert.assertEquals(expected.getCodigo(), found.getCodigo());
        Assert.assertEquals(expected.getNome(), found.getNome());
        Assert.assertEquals(expected.getDescricao(), found.getDescricao());
    }

}
