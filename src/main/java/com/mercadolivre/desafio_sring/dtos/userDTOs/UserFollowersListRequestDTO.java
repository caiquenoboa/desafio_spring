package com.mercadolivre.desafio_sring.dtos.userDTOs;

public class UserFollowersListRequestDTO {
    private Long userId;

    public UserFollowersListRequestDTO() {
    }

    public UserFollowersListRequestDTO(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
