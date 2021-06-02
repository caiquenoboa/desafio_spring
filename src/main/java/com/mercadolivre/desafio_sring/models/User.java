package com.mercadolivre.desafio_sring.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    private String userName;

    @NotNull
    private Boolean isSeller;

    @ManyToMany()
    @JoinTable(name = "FOLLOW", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID_FOLLOW"))
    private List<User> following;

    @ManyToMany(mappedBy = "following")
    private List<User> followers = new ArrayList<>();

    @OneToMany
    private List<Post> posts;

    public User() {
    }

    public User(Long userId, String userName, Boolean isSeller, List<User> following, List<User> followers, List<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.isSeller = isSeller;
        this.following = following;
        this.followers = followers;
        this.posts = posts;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Boolean getIsSeller() {
        return isSeller;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addUsersFollowing(User userToFollow) {
        this.following.add(userToFollow);
    }
}
