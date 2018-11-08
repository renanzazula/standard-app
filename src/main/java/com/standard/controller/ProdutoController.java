package com.standard.controller;

import com.standard.domain.*;
import com.standard.service.categoria.CategoriaService;
import com.standard.service.dominio.DominioService;
import com.standard.service.medida.MedidaService;
import com.standard.service.produto.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    private ProdutoService produtoService;
    private CategoriaService categoriaService;
    private MedidaService medidaService;
    private DominioService dominioService;



    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<Produto>> consultar() {
        return new ResponseEntity<>(produtoService.consultar(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Produto> consultarByCodigo(@PathVariable Integer id) {
        return new ResponseEntity<>(produtoService.consultarByCodigo(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produto> incluir(@RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.incluir(produto), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Produto> delete(@PathVariable Integer id) {
        produtoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Produto> alterar(@PathVariable Integer id, @RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.alterar(id, produto), HttpStatus.OK);
    }

    //fixme:
    @ResponseBody
    @RequestMapping(value = "/ajaxConsultaSubCategoriaByCategoria")
    public List<SubCategoria> consultaSubCategoriaByCategoria(@RequestBody Categoria categoria) {
        Categoria listSubCategorias = categoriaService.consultarByCodigo(categoria.getCodigo());
        return listSubCategorias.getSubCategorias();
    }

    @ResponseBody
    @RequestMapping(value = "/addicionarProduto")
    public Produto addicionarProduto(@RequestBody String barCodep) {
        Produto barCode = new Produto();
        barCode.setBarCode(barCodep);
        Produto produtoDB = produtoService.consultarByBarCode(barCode);

        return  produtoDB;
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxConsultarItensMedidaByCategoria")
    public List<Medida> ajaxConsultarItensMedidaByCategoria(@RequestBody Produto produto) {
        return  medidaService.consultarByCategoriaSubCategoriaMarca(produto);
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxConsultarItensMedidaByProdutoCodigo")
    public List<ProdutoHasItensTipoMedida> ajaxConsultarItensMedidaByProdutoCodigo(@RequestBody Produto produto){
        Produto produtoDB = produtoService.consultarByCodigo(produto.getCodigo());
        return produtoDB.getProdutoHasItensTipoMedida();
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxConsultarItensMedidaByMedidaCodigo")
    public List<ItensTipoMedida> ajaxConsultarItensMedidaByMedidaCodigo(@RequestBody Medida medida){
        Medida medidas = medidaService.consultarByCodigo(medida.getCodigo());
        return medidas.getItensTipoMedida();
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxObterDominios")
    public List<Dominio> ajaxObterDominios(){
        return dominioService.consultar();
    }

}
