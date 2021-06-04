package com.mercadolivre.desafio_sring.dtos.postDTOs.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolivre.desafio_sring.dtos.productDTOs.response.ProductCreateResponseDTO;
import com.mercadolivre.desafio_sring.models.Post;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostCreateResponseDTO {
    private Long id_post;

    @JsonFormat(pattern = "dd-MM-uuuu")
    private LocalDate date;

    private ProductCreateResponseDTO detail;
    private Long category;
    private Double price;

    public PostCreateResponseDTO() {
    }

    public PostCreateResponseDTO(Long id_post, LocalDate date, ProductCreateResponseDTO detail, Long category, Double price) {
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public PostCreateResponseDTO(Post post) {
        this.id_post = post.getId_post();
        this.date = post.getDate();
        this.category = post.getCategory();
        this.price = post.getPrice();
        this.detail = new ProductCreateResponseDTO(post.getProduct());
    }

    public Long getId_post() {
        return id_post;
    }

    public void setId_post(Long id_post) {
        this.id_post = id_post;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ProductCreateResponseDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductCreateResponseDTO detail) {
        this.detail = detail;
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
}
