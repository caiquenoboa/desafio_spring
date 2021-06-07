package com.mercadolivre.desafioSpring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Campo obrigatório")
    private String productName;

    @NotNull(message = "Campo obrigatório")
    private String type;

    @NotNull(message = "Campo obrigatório")
    private String brand;

    @NotNull(message = "Campo obrigatório")
    private String color;

    @NotNull(message = "Campo obrigatório")
    private String notes;
}
