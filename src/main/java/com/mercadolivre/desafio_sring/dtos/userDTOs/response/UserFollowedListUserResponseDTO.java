package com.mercadolivre.desafio_sring.dtos.userDTOs.response;

import com.mercadolivre.desafio_sring.models.User;

public class UserFollowedListUserResponseDTO {
    private Long userId;
    private String userName;

    public UserFollowedListUserResponseDTO() {
    }

    public UserFollowedListUserResponseDTO(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserFollowedListUserResponseDTO(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
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
