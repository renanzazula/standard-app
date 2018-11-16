package com.standard.controller;

import com.standard.domain.*;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.dominio.DominioService;
import com.standard.service.medida.MedidaService;
import com.standard.service.produto.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(ProdutoController.BASE_URL)
public class ProdutoController {

    public static final String BASE_URL = "/api/v1/produto";

    private ProdutoService produtoService;
    private CategoriaService categoriaService;
    private MedidaService medidaService;
    private DominioService dominioService;

    public ProdutoController(ProdutoService produtoService, CategoriaService categoriaService,
                             MedidaService medidaService, DominioService dominioService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.medidaService = medidaService;
        this.dominioService = dominioService;
    }


    @GetMapping({"/all"})
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> consultar() {
        return produtoService.consultar();
    }

    @GetMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public Produto consultarByCodigo(@PathVariable Long codigo) {
        return produtoService.consultarByCodigo(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto incluir(@RequestBody Produto produto) {
        return produtoService.incluir(produto);
    }

    @DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long codigo) {
        produtoService.excluir(codigo);
    }

    @PutMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public Produto alterar(@PathVariable Long codigo, @RequestBody Produto produto) {
        return produtoService.alterar(codigo, produto);
    }

    //fixme:
    @RequestMapping(value = "/ajaxConsultaSubCategoriaByCategoria")
    @ResponseStatus(HttpStatus.OK)
    public List<SubCategoria> consultaSubCategoriaByCategoria(@RequestBody Categoria categoria) {
        Categoria listSubCategorias = categoriaService.consultarByCodigo(categoria.getCodigo());
        return listSubCategorias.getSubCategorias();
    }


    @RequestMapping(value = "/addicionarProduto")
    @ResponseStatus(HttpStatus.OK)
    public Produto addicionarProduto(@RequestBody String barCodep) {
        Produto barCode = new Produto();
        barCode.setBarCode(barCodep);
        Produto produtoDB = produtoService.consultarByBarCode(barCode);

        return produtoDB;
    }

    @RequestMapping(value = "/ajaxConsultarItensMedidaByCategoria")
    @ResponseStatus(HttpStatus.OK)
    public List<Medida> ajaxConsultarItensMedidaByCategoria(@RequestBody Produto produto) {
        return medidaService.consultarByCategoriaSubCategoriaMarca(produto);
    }

    @RequestMapping(value = "/ajaxConsultarItensMedidaByProdutoCodigo")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoHasItensTipoMedida> ajaxConsultarItensMedidaByProdutoCodigo(@RequestBody Produto produto) {
        Produto produtoDB = produtoService.consultarByCodigo(produto.getCodigo());
        return produtoDB.getProdutoHasItensTipoMedida();
    }


    @RequestMapping(value = "/ajaxConsultarItensMedidaByMedidaCodigo")
    @ResponseStatus(HttpStatus.OK)
    public List<ItensTipoMedida> ajaxConsultarItensMedidaByMedidaCodigo(@RequestBody Medida medida) {
        Medida medidas = medidaService.consultarByCodigo(medida.getCodigo());
        return medidas.getItensTipoMedida();
    }


    @RequestMapping(value = "/ajaxObterDominios")
    @ResponseStatus(HttpStatus.OK)
    public List<Dominio> ajaxObterDominios() {
        return dominioService.consultar();
    }

}
