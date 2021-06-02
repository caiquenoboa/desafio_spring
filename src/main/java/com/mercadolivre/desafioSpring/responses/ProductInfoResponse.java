package com.mercadolivre.desafioSpring.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInfoResponse {
    private Integer id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
