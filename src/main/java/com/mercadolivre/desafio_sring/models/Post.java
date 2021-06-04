package com.mercadolivre.desafio_sring.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;

    private LocalDate date;

    @NotNull
    private Long category;

    @NotNull
    private Double price;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Product product;

    public Post() { }

    public Post(Long id_post, LocalDate date, Long category, Double price, User user, Product product) {
        this.id_post = id_post;
        this.date = date != null ? date : LocalDate.now();
        this.category = category;
        this.price = price;
        this.user = user;
        this.product = product;
    }

    public Long getId_post() {
        return id_post;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
}
