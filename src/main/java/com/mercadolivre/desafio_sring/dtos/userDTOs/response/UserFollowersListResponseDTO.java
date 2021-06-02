package com.mercadolivre.desafio_sring.dtos.userDTOs.response;

import java.util.List;

public class UserFollowersListResponseDTO {
    private Long userId;
    private String userName;
    private List<UserFollowersListUserResponseDTO> followers;

    public UserFollowersListResponseDTO() {
    }

    public UserFollowersListResponseDTO(Long userId, String userName, List<UserFollowersListUserResponseDTO> followers) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
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

    public List<UserFollowersListUserResponseDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserFollowersListUserResponseDTO> followers) {
        this.followers = followers;
    }
}
