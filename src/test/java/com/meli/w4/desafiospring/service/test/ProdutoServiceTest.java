package com.meli.w4.desafiospring.service.test;

import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.repository.ProdutoRepository;
import com.meli.w4.desafiospring.service.ProdutoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProdutoServiceTest {

    @Test
    public void deveRegistrarUmProduto() throws IOException {
        List<Produto> produtos = Arrays.asList(
                Produto.builder().productId(1).name("Produto1").brand("Marca1").category("Categoria1").freeShipping(true).price(new BigDecimal(123.00)).quantity(10).prestige("*").build(),
                Produto.builder().productId(2).name("Produto2").brand("Marca1").category("Categoria2").freeShipping(true).price(new BigDecimal(24.00)).quantity(50).prestige("**").build(),
                Produto.builder().productId(3).name("Produto3").brand("Marca2").category("Categoria1").freeShipping(false).price(new BigDecimal(1599.00)).quantity(4).prestige("*****").build(),
                Produto.builder().productId(4).name("Produto4").brand("Marca2").category("Categoria2").freeShipping(true).price(new BigDecimal(1.99)).quantity(1000).prestige("***").build()
        );
        ProdutoRepository mock = Mockito.mock(ProdutoRepository.class);
        Mockito.when(mock.salvarProdutos(produtos)).thenReturn(produtos);
        ProdutoService produtoService = new ProdutoService(mock);

        List<Produto> produtosRegistro = produtoService.setProdutos(produtos);

        Assertions.assertArrayEquals(produtosRegistro.toArray(), produtos.toArray());
    }
}
