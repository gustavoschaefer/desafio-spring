package com.meli.w4.desafiospring.service;

import com.meli.w4.desafiospring.entity.Produto;
import com.meli.w4.desafiospring.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar lista de produtos.");
        }

    }

    public List<Produto> getProdutos(Map<String,String> param){
        //Lista todos os produtos
        List<Produto> produtos = null;
        try{
            produtos = produtoRepository.getProdutos(param);
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar lista de produtos.");
        }
        return produtos;
    }
}
