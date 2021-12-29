package com.meli.w4.desafiospring.repository;

import java.io.IOException;
import java.util.List;

public interface ProdutoInterface <T,U>{

    void salvarProdutos(List<T> t) throws IOException;
    List<T> getProdutos(U params) throws IOException;
}
