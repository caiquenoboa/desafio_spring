package com.mercadolivre.desafioSpring.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "Campo obrigatório")
    private String userName;

    @NotNull(message = "Campo obrigatório")
    private Boolean isSeller;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "userFollowers")
    private List<User> followers;

    @OneToMany(mappedBy = "userFollowed")
    private List<User> followed;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(Integer userId, String userName, Boolean isSeller) {
        this.userId = userId;
        this.userName = userName;
        this.isSeller = isSeller;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getSeller() {
        return isSeller;
    }

    public void setSeller(Boolean seller) {
        isSeller = seller;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowed() {
        return followed;
    }

    public void setFollowed(List<User> followed) {
        this.followed = followed;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
