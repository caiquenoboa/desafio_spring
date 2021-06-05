package com.meli.desafiospring.gateway.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailResponse {

    private Integer productId;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
