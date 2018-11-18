package com.standard.controller;

import com.standard.domain.ItensTipoMedida;
import com.standard.domain.Medida;
import com.standard.domain.Produto;
import com.standard.domain.ProdutoHasItensTipoMedida;
import com.standard.service.medida.MedidaService;
import com.standard.service.produto.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DominioController.BASE_URL)
public class ItensMedidaController {

    public static final String BASE_URL = "/api/v1/itensMedida";

    private final MedidaService medidaService;
    private final ProdutoService produtoService;

    public ItensMedidaController(MedidaService medidaService, ProdutoService produtoService) {
        this.medidaService = medidaService;
        this.produtoService = produtoService;
    }

    // fixme: separar end point...
    @RequestMapping(value = "/ajaxConsultarItensMedidaByCategoria")
    @ResponseStatus(HttpStatus.OK)
    public List<Medida> ajaxConsultarItensMedidaByCategoria(@RequestBody Produto produto) {
        return medidaService.consultarByCategoriaSubCategoriaMarca(produto);
    }

    @GetMapping(value = "/byProduto/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoHasItensTipoMedida> ajaxConsultarItensMedidaByProdutoCodigo(@PathVariable String codigo) {
        Produto produtoDB = produtoService.consultarByCodigo(new Long(codigo));
        return produtoDB.getProdutoHasItensTipoMedida();
    }


    @GetMapping(value = "/byMedida/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public List<ItensTipoMedida> ajaxConsultarItensMedidaByMedidaCodigo(@RequestBody Medida medida, @PathVariable String codigo) {
        Medida medidas = medidaService.consultarByCodigo(medida.getCodigo());
        return medidas.getItensTipoMedida();
    }

}
