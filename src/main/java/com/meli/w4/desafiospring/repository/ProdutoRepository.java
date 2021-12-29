package com.meli.w4.desafiospring.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.w4.desafiospring.entity.Produto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ProdutoRepository implements ProdutoInterface<Produto,Map<String,String>>{

    private List<Produto> produtos = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String  PATH = "src/main/java/com/meli/w4/desafiospring/repository/produtos.json";


    @Override
    public void salvarProdutos(List<Produto> listaProdutos) throws IOException {

        produtos.addAll(listaProdutos);

        objectMapper.writeValue(new File(PATH),produtos);
    }

    @Override
    public List<Produto> getProdutos(Map<String, String> params) throws IOException {
        produtos = objectMapper.readValue(new File(PATH), new TypeReference<ArrayList<Produto>>() {});
        return produtos;
    }
}
