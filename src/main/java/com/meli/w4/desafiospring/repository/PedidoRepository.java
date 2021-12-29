package com.meli.w4.desafiospring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.w4.desafiospring.entity.Pedido;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoRepository {

    private List<Pedido> orders = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "src/main/java/com/meli/w4/desafiospring/repository/pedidos.json";

    public void salvarPedidos(Pedido order) throws IOException{

        orders.add(order);
        objectMapper.writeValue(new File(PATH), orders);
    }

}
