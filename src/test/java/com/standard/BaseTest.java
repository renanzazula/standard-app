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

    private static final String VALOR_P = "P";
    private static final String VALOR_X = "X";
    private static final String VALOR_L = "L";
    private static final String VALOR_XL = "XL";
    private static final int QUANTIDADE = 10;
    private static final long CODIGO = 1L;

    // obj commons
    protected Marca marca = null;
    protected SubCategoria subCategoria = null;
    protected Categoria categoria = null;
    protected Fornecedor fornecedor = null;
    protected Medida medida = null;
    protected List<ItensTipoMedida> itensTipoMedida = null;
    private ItensTipoMedida itenTipoMedida = null;
    protected Dominio dominio = null;

    protected Produto produto = null;
    protected List<ProdutoHasItensTipoMedida> produtoHasItensTipoMedida = null;
    private ProdutoHasItensTipoMedida produtoHasItenTipoMedida = null;

    protected void setUpMarca() {
        marca = new Marca();
        marca.setNome(NOME);
        marca.setDescricao(DESCRICAO);
    }

    protected void setUpSubCategoria() {
        subCategoria = new SubCategoria();
        subCategoria.setNome(NOME);
        subCategoria.setDescricao(DESCRICAO);
    }

    protected void setUpCategoria() {
        categoria = new Categoria();
        categoria.setCodigo(1L);
        categoria.setNome(NOME);
        categoria.setDescricao(DESCRICAO);
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
            //itenTipoMedida.setCodigo(new Long(i));
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
        produto.setStatus(StatusEnum.Ativo);
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

    private void assertProdutoHasItensTipoMedida(List<ProdutoHasItensTipoMedida> produto, List<ProdutoHasItensTipoMedida> found) {
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
            Assert.assertEquals(found.get(i).getItensTipoMedida().getValor(), produto.get(i).getItensTipoMedida().getValor());

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
