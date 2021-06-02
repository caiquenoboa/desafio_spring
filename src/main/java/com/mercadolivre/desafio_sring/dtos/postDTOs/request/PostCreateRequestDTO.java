package com.mercadolivre.desafio_sring.dtos.postDTOs.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolivre.desafio_sring.dtos.productDTOs.request.ProductCreateRequestDTO;
import com.mercadolivre.desafio_sring.models.Post;
import com.mercadolivre.desafio_sring.models.Product;
import com.mercadolivre.desafio_sring.models.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PostCreateRequestDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Long category;

    @NotNull
    private Double price;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull
    @Valid
    private ProductCreateRequestDTO detail;

    public PostCreateRequestDTO() {
    }

    public PostCreateRequestDTO(Long userId, Long category, Double price, LocalDate date, ProductCreateRequestDTO detail) {
        this.userId = userId;
        this.category = category;
        this.price = price;
        this.date = date;
        this.detail = detail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ProductCreateRequestDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductCreateRequestDTO detail) {
        this.detail = detail;
    }

    public Post toModel(User user, Product product) {
        return new Post(null, this.date, this.category, this.price, user, product);
    }
}
