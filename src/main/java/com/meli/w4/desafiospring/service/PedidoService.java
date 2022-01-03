package com.meli.w4.desafiospring.service;

import com.meli.w4.desafiospring.entity.Pedido;
import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.repository.PedidoRepository;
import com.meli.w4.desafiospring.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    List<Produto> estoqueProdutos = new ArrayList<>();

    public Pedido novoPedido(List<Produto> produtos) {

        Map<String, String> params = new HashMap<>();

        try {
            estoqueProdutos = produtoRepository.getProdutos(params);

            Pedido pedido = new Pedido();

            BigDecimal valorTotal = BigDecimal.ZERO;

            for (Produto produto : produtos) {

                Optional<Produto> optionalProduto = estoqueProdutos.stream()
                        .filter(p -> p.getProductId() == produto.getProductId()).findFirst();

                if (optionalProduto.orElse(new Produto()).getProductId() == produto.getProductId()) {

                    if (optionalProduto.get().getQuantity() < produto.getQuantity()) {

                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O produto " + produto.getName()
                                + " contém apenas " + optionalProduto.get().getQuantity() + " unidades em estoque");
                    }

                    BigDecimal subTotal = optionalProduto.get().getPrice().multiply(new BigDecimal(produto.getQuantity()));
                    valorTotal = valorTotal.add(subTotal);
                    produto.setPrice(optionalProduto.get().getPrice());
                    produto.setCategory(optionalProduto.get().getCategory());
                    produto.setFreeShipping(optionalProduto.get().getFreeShipping());
                    produto.setPrestige(optionalProduto.get().getPrestige());
                    for (Produto produtoEstoque : estoqueProdutos ) {
                        int tempquantity;
                        if(produtoEstoque.getProductId() == produto.getProductId()) {
                            tempquantity = produtoEstoque.getQuantity() - produto.getQuantity();
                            produtoEstoque.setQuantity(tempquantity);
                        }
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
                }
            }

            pedido.setProdutos(produtos);
            pedido.setTotal(valorTotal);
            pedidoRepository.salvarPedidos(pedido);
            return pedido;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar produtos do estoque.");
        }

    }

}
