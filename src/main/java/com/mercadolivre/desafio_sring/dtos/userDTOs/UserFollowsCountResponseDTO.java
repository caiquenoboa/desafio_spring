package com.mercadolivre.desafio_sring.dtos.userDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFollowsCountResponseDTO {
    private Long userId;
    private String userName;
    private Long followers_count;

    public UserFollowsCountResponseDTO() {
    }

    public UserFollowsCountResponseDTO(Long userId, String userName, Long followers_count) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(Long followers_count) {
        this.followers_count = followers_count;
    }
}
