package com.meli.w4.desafiospring.service;

import com.meli.w4.desafiospring.entity.Carrinho;
import com.meli.w4.desafiospring.entity.Pedido;
import com.meli.w4.desafiospring.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CarrinhoService {
    Carrinho carrinho = new Carrinho();
    List<Pedido> listPedidos = new ArrayList<>();
    BigDecimal subTotalCarrinho = BigDecimal.ZERO;

    @Autowired
    PedidoService pedidoService;

    public Carrinho incluiPedidoCarrinho(List<Produto> produtos){
        Pedido pedido = pedidoService.novoPedido(produtos);
        listPedidos.add(pedido);
        carrinho.setPedidosCarrinho(listPedidos);
        subTotalCarrinho = subTotalCarrinho.add(pedido.getTotal());
        carrinho.setValorTotalCarrinho(subTotalCarrinho);

        return carrinho;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

}
