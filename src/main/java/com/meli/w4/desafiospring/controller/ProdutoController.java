package com.meli.w4.desafiospring.controller;

import com.meli.w4.desafiospring.DTO.ProdutoDTO;
import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/salvar")
    public ResponseEntity<List<Produto>> postProdutos(@RequestBody List<Produto> produtos, UriComponentsBuilder uriBuilder)  {
        produtoService.setProdutos(produtos);
        URI uri = uriBuilder
                .path("/produtos/produtoslista")
                .build().toUri();
        return ResponseEntity.created(uri).body(produtos);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDTO>> listaProdutos(@RequestParam Map<String,String> param){

        return ResponseEntity.ok(ProdutoDTO.converteDTO(produtoService.getProdutos(param)));
    }

}
