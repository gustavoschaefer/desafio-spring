package com.meli.w4.desafiospring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private long productId;
    private String name, category, brand, prestige;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeShipping;

}
