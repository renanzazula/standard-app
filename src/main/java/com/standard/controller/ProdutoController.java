package com.standard.controller;

import com.standard.domain.Produto;
import com.standard.service.produto.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(ProdutoController.BASE_URL)
public class ProdutoController {

    public static final String BASE_URL = "/api/v1/produto";

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping({""})
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

    @RequestMapping(value = "/addicionarProduto/{barCode}")
    @ResponseStatus(HttpStatus.OK)
    public Produto addicionarProduto(@PathVariable String barCode) {
        return produtoService.consultarByBarCode(barCode);
    }


}
