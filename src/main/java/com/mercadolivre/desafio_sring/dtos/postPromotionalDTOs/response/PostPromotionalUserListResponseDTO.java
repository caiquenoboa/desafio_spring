package com.mercadolivre.desafio_sring.dtos.postPromotionalDTOs.response;

import com.mercadolivre.desafio_sring.models.PostPromotional;

import java.util.List;
import java.util.stream.Collectors;

public class PostPromotionalUserListResponseDTO {
    private Long userId;
    private String userName;
    private List<PostPromotionalCreateResponseDTO> posts;

    public PostPromotionalUserListResponseDTO() {

    }

    public PostPromotionalUserListResponseDTO(Long userId, String userName, List<PostPromotionalCreateResponseDTO> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
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

    public List<PostPromotionalCreateResponseDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostPromotionalCreateResponseDTO> posts) {
        this.posts = posts;
    }
}
