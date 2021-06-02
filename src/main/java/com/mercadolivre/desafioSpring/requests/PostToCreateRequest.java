package com.mercadolivre.desafioSpring.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostToCreateRequest {
    private Integer sellerId;
    private Integer idPost;
    private LocalDate date;
    private ProductToCreateRequest productToCreateRequest;
    private Integer category;
    private Double price;

}
