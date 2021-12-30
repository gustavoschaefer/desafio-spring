package com.meli.w4.desafiospring.entity;

import java.math.BigDecimal;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    public static Comparator<Produto> ordemAlfabeticaCrescente = new Comparator<Produto>() {
		@Override
		public int compare(Produto p1, Produto p2) {
			return p1.getName().compareTo(p2.getName());
		}    	
    };
    
    public static Comparator<Produto> ordemAlfabeticaDecrescente = new Comparator<Produto>() {
		@Override
		public int compare(Produto p1, Produto p2) {
			return p2.getName().compareTo(p1.getName());
		}    	
    };
    
    public static Comparator<Produto> ordemCrescentePreco = new Comparator<Produto>() {
		@Override
		public int compare(Produto p1, Produto p2) {
			return p1.getPrice().compareTo(p2.getPrice());
		}    	
    };
    
    public static Comparator<Produto> ordemDecrescentePreco = new Comparator<Produto>() {
		@Override
		public int compare(Produto p1, Produto p2) {
			return p2.getPrice().compareTo(p1.getPrice());
		}    	
    };

    


}
