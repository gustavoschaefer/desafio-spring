package com.meli.w4.desafiospring.service;

import com.meli.w4.desafiospring.entity.Pedido;
import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.repository.PedidoRepository;
import com.meli.w4.desafiospring.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido newOrder(List<Produto> products) throws IOException {

        Map<String,String> params = null;

        List<Produto> estoqueProdutos = produtoRepository.getProdutos(params);

        for (Produto produto : products){

            if (estoqueProdutos.contains(produto)){

            }

        }
        return null;
    }
}
