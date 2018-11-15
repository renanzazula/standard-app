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

    protected void assertMarcaSubCategoriaCategoriaValor(Medida medida) {
        for (int j = 0; j < medida.getItensTipoMedida().size(); j++) {

            Marca marcaFound = medida.getItensTipoMedida().get(j).getMarca();
            assertMarca(marcaFound, marca);

            SubCategoria subCategoriaFound = medida.getItensTipoMedida().get(j).getSubCategoria();
            assertSubCategoria(subCategoriaFound, subCategoria);

            Categoria categoriaFound = medida.getItensTipoMedida().get(j).getCategoria();
            assertCategoria(categoriaFound, categoria);

            Assert.assertEquals(medida.getItensTipoMedida().get(j).getValor(), medida.getItensTipoMedida().get(j).getValor());
        }
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

    protected void assertDominios(Dominio expected, Dominio found) {
        Assert.assertEquals(expected.getCodigo(), found.getCodigo());
        Assert.assertEquals(expected.getNome(), found.getNome());
        Assert.assertEquals(expected.getDescricao(), found.getDescricao());
        Assert.assertEquals(expected.isChecked(), found.isChecked());
    }

    protected void assertProdutoHasItensTipoMedida(List<ProdutoHasItensTipoMedida> produto, List<ProdutoHasItensTipoMedida> found) {
        for (int i = 0; i < found.size(); i++) {
            Assert.assertEquals(found.get(i).getCodigo(), produto.get(i).getCodigo());
            Assert.assertEquals(found.get(i).getDominios().size(),
                    produto.get(i).getDominios().size());

            for (int j = 0; j < found.get(i).getDominios().size(); j++) {
                assertDominios(found.get(i).getDominios().get(j),
                        produto.get(i).getDominios().get(j));
            }

            Assert.assertEquals(found.get(i).getQuantidade(), produto.get(i).getQuantidade());
            Assert.assertEquals(found.get(i).getValorUnitario(), produto.get(i).getValorUnitario());
            Assert.assertEquals(found.get(i).getItensTipoMedida(), produto.get(i).getItensTipoMedida());
            Assert.assertEquals(found.get(i).getProduto(), produto.get(i).getProduto());
        }
    }
    
    protected void assertProduto (Produto found, Produto expected){
        Assert.assertEquals(found.getCodigo(), expected.getCodigo());
        Assert.assertEquals(found.getBarCode(), expected.getBarCode());
        Assert.assertEquals(found.getNome(), expected.getNome());
        Assert.assertEquals(found.getStatus(), expected.getStatus());
        Assert.assertEquals(found.getDescricao(), expected.getDescricao());
        Assert.assertEquals(found.getPreco(), expected.getPreco());
        Assert.assertEquals(found.getPrecoVenda(), expected.getPrecoVenda());
        Assert.assertEquals(found.getPreco(), expected.getPreco());
        Assert.assertEquals(found.getPrecoCusto(), expected.getPrecoCusto());
        Assert.assertEquals(found.getPrecoOferta(), expected.getPrecoOferta());
        Assert.assertEquals(found.getDesconto(), expected.getDesconto());
        Assert.assertEquals(found.getPeso(), expected.getPeso());
        Assert.assertEquals(found.getPorcentagem(), expected.getPorcentagem());
        Assert.assertEquals(found.getPorcentagemDesconto(), expected.getPorcentagemDesconto());
        assertMarca(found.getMarca(), expected.getMarca());
        assertCategoria(found.getCategoria(), expected.getCategoria());
        assertSubCategoria(found.getSubCategoria(), expected.getSubCategoria());
        assertFornecedor(found.getFornecedor(), expected.getFornecedor());
        assertMarcaSubCategoriaCategoriaValor(found.getMedida());
        Assert.assertEquals(found.getProdutoHasItensTipoMedida().size(), expected.getProdutoHasItensTipoMedida().size());
        assertProdutoHasItensTipoMedida(found.getProdutoHasItensTipoMedida(), expected.getProdutoHasItensTipoMedida());
    }

    protected void asserItensTipoMedida(ItensTipoMedida found, ItensTipoMedida expected){
         Assert.assertEquals(found.getCodigo(), expected.getCodigo());
         Assert.assertEquals(found.getValor(), expected.getValor());
         Assert.assertEquals(found.getMedida(), expected.getMedida());
         Assert.assertEquals(found.getMarca(), expected.getMarca());
         Assert.assertEquals(found.getCategoria(), expected.getCategoria());
         Assert.assertEquals(found.getSubCategoria(), expected.getSubCategoria());
    }

}
