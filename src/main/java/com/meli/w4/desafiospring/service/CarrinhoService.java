package com.meli.w4.desafiospring.service;


import com.meli.w4.desafiospring.entity.Carrinho;
import com.meli.w4.desafiospring.entity.Pedido;
import com.meli.w4.desafiospring.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {
    Carrinho carrinho = new Carrinho();
    List<Pedido> listPedidos = new ArrayList<>();
    BigDecimal subTotalCarrinho = BigDecimal.ZERO;

    @Autowired
    PedidoService pedidoService;

    public Carrinho incluiPedidoCarrinho(List<Produto> produtos){
        Pedido pedido = pedidoService.newOrder(produtos);
        listPedidos.add(pedido);
        carrinho.setPedidosCarrinho(listPedidos);
        subTotalCarrinho = subTotalCarrinho.add(pedido.getTotal());
        carrinho.setValorTotalCarrinho(subTotalCarrinho);

        return carrinho;
    }

}
