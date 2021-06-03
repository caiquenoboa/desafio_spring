package com.meli.desafiospring.gateway.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailRequest {

    @NotBlank(message = "productName cannot be null or empty")
    private String productName;

    @NotBlank(message = "type cannot be null or empty")
    private String type;

    @NotBlank(message = "brand cannot be null or empty")
    private String brand;

    @NotBlank(message = "color cannot be null or empty")
    private String color;

    @NotBlank(message = "notes cannot be null or empty")
    private String notes;
}
