package com.meli.w4.desafiospring.service;

import com.meli.w4.desafiospring.entity.Cliente;
import com.meli.w4.desafiospring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void setCliente(Cliente cliente) {

        fieldValidation(cliente);
        try {
            clienteRepository.setCliente(cliente);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar cliente.");
        }
    }

    public List<Cliente> getClientes(Map<String,String> param) {
        List<Cliente> clientes = null;
        try{
            clientes = clienteRepository.getClientes(param);
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar lista de clientes.");
        }
        return clientes;
    }

    public void fieldValidation(Cliente cliente) {
        List<Cliente> clientes = null;
        Map<String,String> param = new HashMap<>();

        if (cliente.getNome() == null
                || cliente.getEmail() == null
                || cliente.getTelefone() == null
                || cliente.getCpf() == null
                || cliente.getEstado() == null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Todos os campos são de preenchimento obrigatório.");
        }

        try {
            clientes = clienteRepository.getClientes(param);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista vazia.");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao buscar lista de clientes.");
        }
        for (Cliente cli : clientes) {
            if (cliente.getCpf().equals(cli.getCpf())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "O cpf já está cadastrado para outro usuário.");
            }
        }
    }
}
