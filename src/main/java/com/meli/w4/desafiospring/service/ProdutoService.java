package com.meli.w4.desafiospring.service;

import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void setProdutos(List<Produto> produtos) {
        try {
            produtoRepository.salvarProdutos(produtos);
        } catch (IOException e){
            System.out.println("deu ruim");
        }

    }

    public List<Produto> getProdutos(Map<String,String> param) throws IOException{

        return null;
    }
}
