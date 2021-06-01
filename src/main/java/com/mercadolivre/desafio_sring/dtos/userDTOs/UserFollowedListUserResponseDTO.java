package com.mercadolivre.desafio_sring.dtos.userDTOs;

public class UserFollowedListUserResponseDTO {
    private Long userId;
    private String userName;

    public UserFollowedListUserResponseDTO() {
    }

    public UserFollowedListUserResponseDTO(Long userId, String userName) {
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
