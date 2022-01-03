package com.meli.w4.desafiospring.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.meli.w4.desafiospring.entity.Cliente;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ClienteRepository {

    private List<Cliente> clientes = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "src/main/java/com/meli/w4/desafiospring/repository/clientes.json";

    public void setCliente(Cliente cliente) throws IOException {
        Long id = new Long(clientes.size() + 1);
        cliente.setId(id);
        clientes.add(cliente);
        objectMapper.writeValue(new File(PATH), clientes);
    }

    public List<Cliente> getClientes(Map<String, String> param) throws NullPointerException, IOException {
        try {
            clientes = objectMapper.readValue(new File(PATH), new TypeReference<ArrayList<Cliente>>() {
            });
        } catch (MismatchedInputException e) {
        }
        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (entry.getKey().equals("estado")) {
                this.clientes = this.clientes.stream().filter(c -> c.getEstado().toString().equals(entry.getValue())).collect(Collectors.toList());
            }
        }
        return this.clientes;
    }

}
