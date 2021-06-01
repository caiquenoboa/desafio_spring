package com.mercadolivre.desafioSpring.dtos;

import com.mercadolivre.desafioSpring.models.User;

import java.util.List;

public class ResponseUserFollowersDTO extends ResponseDTO{

    private Integer userId;
    private String userName;
    private List<User> followers;

    public ResponseUserFollowersDTO(Integer code, String message, Integer userId, String userName,
                                    List<User> followers) {
        super(code, message);
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
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

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}

