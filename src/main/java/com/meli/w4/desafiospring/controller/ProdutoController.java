package com.meli.w4.desafiospring.controller;

import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

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
}
