package com.mercadolivre.desafioSpring.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private User user;

    @NotNull(message = "Campo obrigatório")
    private Date date;

    @Column(nullable = false)
    @ManyToMany()
        @JoinTable(name = "POST_PRODUCTS", joinColumns = @JoinColumn(name = "POST_ID"),
               inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> products;

    @NotNull(message = "Campo obrigatório")
    private Integer category;

    @NotNull(message = "Campo obrigatório")
    private Double price;

    public Post() {
    }

    public Post(Integer postId, User user, Date date, Integer category, Double price) {
        this.postId = postId;
        this.user = user;
        this.date = date;
        this.category = category;
        this.price = price;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}


