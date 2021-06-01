package com.mercadolivre.desafio_sring.dtos.userDTOs;

public class UserFollowsCountRequestDTO {
    private Long userId;

    public UserFollowsCountRequestDTO() {
    }

    public UserFollowsCountRequestDTO(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
