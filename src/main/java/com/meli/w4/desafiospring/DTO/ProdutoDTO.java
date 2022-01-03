package com.meli.w4.desafiospring.DTO;

import com.meli.w4.desafiospring.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String name;
    private Integer quantity;


    public static ProdutoDTO converteDTO(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getProductId());
        produtoDTO.setName(produto.getName());
        produtoDTO.setQuantity(produto.getQuantity());

        return produtoDTO;

    }

    public static List<ProdutoDTO> converteDTO(List<Produto> produtos){
        return produtos.stream().map(u -> converteDTO(u)).collect(Collectors.toList());
    }

}
