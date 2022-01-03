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

    private List<Pedido> pedidos = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "src/main/java/com/meli/w4/desafiospring/repository/pedidos.json";

    public void salvarPedidos(Pedido pedido) throws IOException{
        Long id =  new Long(pedidos.size()+1);
        pedido.setId(id);
        pedidos.add(pedido);
        objectMapper.writeValue(new File(PATH), pedidos);
    }

}
