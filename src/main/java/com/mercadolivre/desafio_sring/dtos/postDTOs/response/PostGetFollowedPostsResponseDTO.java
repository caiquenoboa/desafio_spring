package com.mercadolivre.desafio_sring.dtos.postDTOs.response;

import java.util.List;

public class PostGetFollowedPostsResponseDTO {
    private Long userId;
    private List<PostCreateResponseDTO> posts;

    public PostGetFollowedPostsResponseDTO() {
    }

    public PostGetFollowedPostsResponseDTO(Long userId, List<PostCreateResponseDTO> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PostCreateResponseDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostCreateResponseDTO> posts) {
        this.posts = posts;
    }
}
