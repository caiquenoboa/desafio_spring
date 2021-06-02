package com.mercadolivre.desafio_sring.dtos.userDTOs.response;

import java.util.List;

public class UserFollowedListResponseDTO {
    private Long userId;
    private String userName;
    private List<UserFollowedListUserResponseDTO> following;

    public UserFollowedListResponseDTO() {
    }

    public UserFollowedListResponseDTO(Long userId, String userName, List<UserFollowedListUserResponseDTO> following) {
        this.userId = userId;
        this.userName = userName;
        this.following = following;
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

    public List<UserFollowedListUserResponseDTO> getFollowing() {
        return following;
    }

    public void setFollowing(List<UserFollowedListUserResponseDTO> following) {
        this.following = following;
    }
}
