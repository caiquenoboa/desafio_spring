package com.mercadolivre.desafioSpring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="SELLER_ID", nullable=false)
    private Seller seller;

    @NotNull(message = "Campo obrigatório")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @OneToOne
    private Product product;

    @NotNull(message = "Campo obrigatório")
    private Integer category;

    @NotNull(message = "Campo obrigatório")
    private Double price;
}


