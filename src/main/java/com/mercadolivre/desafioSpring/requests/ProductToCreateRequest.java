package com.mercadolivre.desafioSpring.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductToCreateRequest {
    @NotBlank(message = "O produto deve ter um nome")
    private String productName;
    @NotBlank(message = "O produto deve ter um tipo")
    private String type;
    @NotBlank(message = "O produto deve ter uma marca")
    private String brand;
    @NotBlank(message = "O produto deve ter uma cor")
    private String color;
    @NotBlank(message = "O produto deve ter uma nota")
    private String notes;
}
