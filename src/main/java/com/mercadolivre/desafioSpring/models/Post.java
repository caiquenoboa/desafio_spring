package com.mercadolivre.desafioSpring.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @OneToOne
    private Product product;

    @NotNull(message = "Campo obrigatório")
    private Integer category;

    @NotNull(message = "Campo obrigatório")
    private Double price;

    @NotNull(message = "Campo obrigatório")
    private Boolean hasPromo;

    @NotNull(message = "Campo obrigatório")
    @Min(0)
    private Double discount;

    public Post() {
    }

    public Post(Integer postId, User user, Date date, Product product, Integer category, Double price, Boolean hasPromo, Double discount) {
        this.postId = postId;
        this.user = user;
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}


