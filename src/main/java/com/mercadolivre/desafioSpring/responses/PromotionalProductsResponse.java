package com.mercadolivre.desafioSpring.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromotionalProductsResponse {
    private Integer userId;
    private String userName;
    private Integer promoproducts_count;
}
