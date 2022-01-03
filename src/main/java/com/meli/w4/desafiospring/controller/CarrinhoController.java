package com.meli.w4.desafiospring.controller;

import com.meli.w4.desafiospring.entity.Carrinho;
import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    @Autowired
    CarrinhoService carrinhoService;

    @PostMapping("/incluir")
    public ResponseEntity<Carrinho> incluirNoCarrinho(@RequestBody List<Produto> produtos, UriComponentsBuilder uriComponentsBuilder) {
        URI uri = uriComponentsBuilder
                .path("/carrinho/mostrar")
                .build().toUri();
        return ResponseEntity.created(uri).body(carrinhoService.incluiPedidoCarrinho(produtos));
    }

    @GetMapping("/mostrar")
    public ResponseEntity<Carrinho> mostraCarrinho() {
        return ResponseEntity.ok(carrinhoService.getCarrinho());
    }

}
