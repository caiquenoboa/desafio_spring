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

    @Column(columnDefinition = "boolean default false")
    @NotNull
    private Boolean seller;

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="USER_FOLLOWERS", joinColumns = @JoinColumn(name="USER_ID")
            , inverseJoinColumns = @JoinColumn(name="FOLLOWER_ID"))
    private List<User> followers;

    @ManyToMany(mappedBy = "followers")
    private List<User> followed;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(Integer userId, String userName, Boolean seller, List<User> followers, List<User> followed, List<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.seller = seller;
        this.followers = followers;
        this.followed = followed;
        this.posts = posts;
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
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
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
