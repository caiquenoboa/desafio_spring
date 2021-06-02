package com.meli.desafiospring.gateway.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    public UserResponse(Integer userId, String userName, Integer followersCount) {
        this.userId = userId;
        this.userName = userName;
        this.followersCount = followersCount;
    }

    public UserResponse(Integer userId, String userName, List<UserResponse> followers) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
    }

    public UserResponse(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }


    private Integer userId;
    private String userName;
    private Integer followersCount;
    private List<UserResponse> followers;
    private List<UserResponse> followed;
}
