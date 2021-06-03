package com.mercadolivre.desafioSpring.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductToCreateRequest {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
