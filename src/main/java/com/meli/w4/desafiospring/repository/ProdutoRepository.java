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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        for (Map.Entry<String,String> entry : params.entrySet()) {
            if (entry.getKey().equals("category")) {
                produtos = produtos.stream().filter(p -> p.getCategory().equals(entry.getValue())).collect(Collectors.toList());
            }
            if (entry.getKey().equals("name")) {
                produtos = produtos.stream().filter(p -> p.getName().equals(entry.getValue())).collect(Collectors.toList());
            }
            if (entry.getKey().equals("brand")) {
                produtos = produtos.stream().filter(p -> p.getBrand().equals(entry.getValue())).collect(Collectors.toList());
            }
            if (entry.getKey().equals("price")) {
                produtos = produtos.stream().filter(p -> p.getPrice().toString()
                        .equals(entry.getValue()))
                        .collect(Collectors.toList());
            }
            if (entry.getKey().equals("freeShipping")) {
                produtos = produtos.stream().filter(p -> p.getFreeShipping() == Boolean.valueOf(entry.getValue()))
                        .collect(Collectors.toList());
            }
            if (entry.getKey().equals("prestige")) {
                produtos = produtos.stream().filter(p -> p.getPrestige().equals(entry.getValue())).collect(Collectors.toList());
            }
        }
        return produtos;
    }
}
