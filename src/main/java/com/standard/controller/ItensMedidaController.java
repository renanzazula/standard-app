package com.standard.controller;

import com.standard.domain.ItensTipoMedida;
import com.standard.domain.Medida;
import com.standard.domain.Produto;
import com.standard.domain.ProdutoHasItensTipoMedida;
import com.standard.service.medida.MedidaService;
import com.standard.service.produto.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(DominioController.BASE_URL)
public class ItensMedidaController {

    public static final String BASE_URL = "/private/v1/itensMedida";

    private final MedidaService medidaService;
    private final ProdutoService produtoService;

    // fixme: separar end point...
    @RequestMapping(value = "/ajaxConsultarItensMedidaByCategoria")
    public ResponseEntity<List<Medida>> ajaxConsultarItensMedidaByCategoria(@RequestBody Produto produto) {
        return new ResponseEntity<>(medidaService.consultarByCategoriaSubCategoriaMarca(produto), HttpStatus.OK);
    }

    @GetMapping(value = "/byProduto/{codigo}")
    public  ResponseEntity<List<ProdutoHasItensTipoMedida>> ajaxConsultarItensMedidaByProdutoCodigo(@PathVariable String codigo) {
        Produto produtoDB = produtoService.consultarByCodigo(Long.valueOf(codigo));
        return new ResponseEntity<>(produtoDB.getProdutoHasItensTipoMedida(), HttpStatus.OK);
    }

    @GetMapping(value = "/byMedida/{codigo}")
    public ResponseEntity<List<ItensTipoMedida>> ajaxConsultarItensMedidaByMedidaCodigo(@RequestBody Medida medida, @PathVariable String codigo) {
        Medida medidas = medidaService.consultarByCodigo(medida.getCodigo());
        return new ResponseEntity<>(medidas.getItensTipoMedida(), HttpStatus.OK);
    }

}
