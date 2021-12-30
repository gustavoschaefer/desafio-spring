package com.meli.w4.desafiospring.service;

import com.meli.w4.desafiospring.entity.Pedido;
import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.repository.PedidoRepository;
import com.meli.w4.desafiospring.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido newOrder(List<Produto> products)  {

        Map<String, String> params = new HashMap<>();

        try {
            List<Produto> estoqueProdutos = produtoRepository.getProdutos(params);

            Pedido pedido = new Pedido();

            BigDecimal valorTotal = BigDecimal.ZERO;

            for (Produto produto : products) {

                Optional<Produto> optionalProduto = estoqueProdutos.stream()
                        .filter(p -> p.getProductId() == produto.getProductId()).findFirst();

                if (optionalProduto.orElse(new Produto()).getProductId() == produto.getProductId()) {

                    if (optionalProduto.get().getQuantity() < produto.getQuantity()) {

                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O produto " + produto.getName()
                                + " contém apenas " + optionalProduto.get().getQuantity() + " unidades em estoque");
                    }

                    BigDecimal subTotal = optionalProduto.get().getPrice().multiply(new BigDecimal(produto.getQuantity()));
                    valorTotal = valorTotal.add(subTotal);


                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
                }
            }

            pedido.setProducts(products);
            pedido.setTotal(valorTotal);
            return pedido;
        } catch (IOException e) {

        }

        return null;
    }
}
