package com.meli.w4.desafiospring.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.meli.w4.desafiospring.entity.Cliente;
import com.meli.w4.desafiospring.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/salvar")
    public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente, UriComponentsBuilder uriBuilder)  {
        clienteService.setCliente(cliente);
        URI uri = uriBuilder
                .path("/cliente/listar")
                .build().toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listClientes(@RequestParam Map<String,String> param){
        return ResponseEntity.ok(clienteService.getClientes(param));
    }
}
