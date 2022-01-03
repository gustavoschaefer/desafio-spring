package com.meli.w4.desafiospring.controller;

import com.meli.w4.desafiospring.entity.Pedido;
import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping("/salvar")
    public ResponseEntity<Pedido> postPedido(@RequestBody List<Produto> produtos, UriComponentsBuilder uriComponentsBuilder) {
        URI uri = uriComponentsBuilder
                .path("/pedido/listapedidos")
                .build().toUri();
        return ResponseEntity.created(uri).body(pedidoService.novoPedido(produtos));
    }
}
