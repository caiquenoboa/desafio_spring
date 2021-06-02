package com.mercadolivre.desafio_sring.dtos.postDTOs;

public class PostGetFollowedPostsRequestDTO {
    private Long userId;

    public PostGetFollowedPostsRequestDTO() {
    }

    public PostGetFollowedPostsRequestDTO(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
