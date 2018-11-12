package com.standard;

import com.standard.domain.Categoria;
import com.standard.domain.Marca;
import com.standard.domain.SubCategoria;
import org.junit.Assert;

public class BaseTest {

    protected static final String NOME = "nome";
    protected static final String DESCRICAO = "Descriçao";
    protected static final String NOME_UPDATE = "nomeUpdate";
    protected static final String DESCRICAO_UPDATE = "descricaoUpdate";
    protected static final int PORCENTAGEM_DESCONTO = 15;
    protected static final int PORCENTAGEM_DESCONTO_UPDATE = 15;

    protected static final String VALOR_P  = "P";
    protected static final String VALOR_X  = "X";
    protected static final String VALOR_L  = "L";
    protected static final String VALOR_XL = "XL";

    protected void assertMarca(Marca expected, Marca found){
        Assert.assertEquals(expected.getCodigo(),    found.getCodigo());
        Assert.assertEquals(expected.getNome(),      found.getNome());
        Assert.assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertCategoria(Categoria expected, Categoria found){
        Assert.assertEquals(expected.getCodigo(),    found.getCodigo());
        Assert.assertEquals(expected.getNome(),      found.getNome());
        Assert.assertEquals(expected.getDescricao(), found.getDescricao());
    }

    protected void assertSubCategoria(SubCategoria expected, SubCategoria found){
        Assert.assertEquals(expected.getCodigo(),    found.getCodigo());
        Assert.assertEquals(expected.getNome(),      found.getNome());
        Assert.assertEquals(expected.getDescricao(), found.getDescricao());
    }

}
