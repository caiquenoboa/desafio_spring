package com.mercadolivre.desafio_sring.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @NotBlank
    private String productName;

    @NotBlank
    private String type;

    @NotBlank
    private String brand;

    @NotBlank
    private String color;

    @NotBlank
    private String notes;

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Post post;

    public Product() {
    }

    public Product(Long product_id, String productName, String type, String brand, String color, String notes, Post post) {
        this.product_id = product_id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
        this.post = post;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public String getProductName() {
        return productName;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getNotes() {
        return notes;
    }

    public Post getPost() {
        return post;
    }
}
