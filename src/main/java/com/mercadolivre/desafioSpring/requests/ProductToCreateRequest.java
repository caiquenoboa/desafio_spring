package com.mercadolivre.desafioSpring.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductToCreateRequest {
    @NotNull(message = "O produto deve ter um nome")
    private String productName;
    @NotNull(message = "O produto deve ter um tipo")
    private String type;
    @NotNull(message = "O produto deve ter uma marca")
    private String brand;
    @NotNull(message = "O produto deve ter uma cor")
    private String color;
    @NotNull(message = "O produto deve ter uma nota")
    private String notes;
}
