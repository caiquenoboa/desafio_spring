package com.mercadolivre.desafio_sring.dtos.userDTOs;

public class UserFollowedListRequestDTO {
    private Long userId;

    public UserFollowedListRequestDTO() {
    }

    public UserFollowedListRequestDTO(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
