package com.mercadolivre.desafioSpring.dtos;

public class ResponseUserFollowersCountDTO extends ResponseDTO{

    private Integer userId;
    private String userName;
    private Integer followersCount;

    public ResponseUserFollowersCountDTO(Integer code, String message, Integer userId,
                                         String userName, Integer followersCount) {
        super(code, message);
        this.userId = userId;
        this.userName = userName;
        this.followersCount = followersCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }
}
