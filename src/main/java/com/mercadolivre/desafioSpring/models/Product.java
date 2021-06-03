package com.mercadolivre.desafioSpring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Campo obrigatório")
    private String productName;

    @NotBlank(message = "Campo obrigatório")
    private String type;

    @NotBlank(message = "Campo obrigatório")
    private String brand;

    @NotBlank(message = "Campo obrigatório")
    private String color;

    @NotBlank(message = "Campo obrigatório")
    private String notes;
}
