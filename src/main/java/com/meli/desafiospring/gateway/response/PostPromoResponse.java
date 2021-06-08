package com.meli.desafiospring.gateway.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostPromoResponse extends UserResponse{

    private int promoproductsCount;
    private List<PostResponse> posts;

    public PostPromoResponse(int userId, String userName, int promoproductsCount) {
        super.setUserId(userId);
        super.setUserName(userName);
        this.promoproductsCount = promoproductsCount;
    }

    public PostPromoResponse(int userId, String userName, List<PostResponse> posts) {
        super.setUserId(userId);
        super.setUserName(userName);
        this.posts = posts;
    }

}
