package com.mercadolivre.desafio_sring.dtos.userDTOs;

public class UserFollowersRequestDTO {
    private Long userId;
    private Long userIdToFollow;

    public UserFollowersRequestDTO() {
    }

    public UserFollowersRequestDTO(Long userId, Long userIdToFollow) {
        this.userId = userId;
        this.userIdToFollow = userIdToFollow;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserIdToFollow() {
        return userIdToFollow;
    }

    public void setUserIdToFollow(Long userIdToFollow) {
        this.userIdToFollow = userIdToFollow;
    }
}
