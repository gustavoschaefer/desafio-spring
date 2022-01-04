package com.meli.w4.desafiospring.repository;

import java.io.IOException;
import java.util.List;

public interface ProdutoInterface <T,U>{

    List<T>  salvarProdutos(List<T> t) throws IOException;
    List<T> getProdutos(U params) throws IOException;
}
