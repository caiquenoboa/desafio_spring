package com.meli.desafiospring.gateway.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meli.desafiospring.config.LocalDateDeserializer;
import com.meli.desafiospring.config.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRequest {

    @NotNull(message = "userId cannot be null or empty")
    private Integer userId;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @Valid
    private DetailRequest detail;

    private int category;
    private double price;

    private Boolean hasPromo;
    private Double discount;
}
