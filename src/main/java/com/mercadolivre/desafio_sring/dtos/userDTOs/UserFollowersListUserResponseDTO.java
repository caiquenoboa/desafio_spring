package com.mercadolivre.desafio_sring.dtos.userDTOs;

public class UserFollowersListUserResponseDTO {
    private Long userId;
    private String userName;

    public UserFollowersListUserResponseDTO() {
    }

    public UserFollowersListUserResponseDTO(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
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
}
