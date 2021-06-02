package com.mercadolivre.desafio_sring.dtos.productDTOs;

import com.mercadolivre.desafio_sring.models.Product;

import javax.validation.constraints.NotBlank;

public class ProductCreateRequestDTO {
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

    public ProductCreateRequestDTO() {
    }

    public ProductCreateRequestDTO(String productName, String type, String brand, String color, String notes) {
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Product toModel() {
        return new Product(null, this.productName, this.type, this.brand, this.color, this.notes, null);
    }
}
