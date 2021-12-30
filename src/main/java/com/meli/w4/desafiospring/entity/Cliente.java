package com.meli.w4.desafiospring.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    long id;
    String nome, cpf, email, telefone;
    Estado estado;


}
