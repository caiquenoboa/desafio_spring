package com.meli.desafiospring.gateway.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meli.desafiospring.config.LocalDateDeserializer;
import com.meli.desafiospring.config.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {

    public PostResponse(DetailResponse detailResponse) {
        this.detail = detailResponse;
    }

    private Integer idPost;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private DetailResponse detail;
    private int category;

    private double price;

    private Boolean hasPromo;

    private Double discount;
}
