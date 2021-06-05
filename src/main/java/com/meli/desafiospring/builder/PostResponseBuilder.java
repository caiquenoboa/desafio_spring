package com.meli.desafiospring.builder;

import com.meli.desafiospring.gateway.response.DetailResponse;
import com.meli.desafiospring.gateway.response.PostResponse;
import com.meli.desafiospring.model.Post;
import org.springframework.beans.BeanUtils;

public class PostResponseBuilder {

    public static PostResponse builder(Post post){
        DetailResponse detailResponse = new DetailResponse();
        PostResponse postResponse = new PostResponse(detailResponse);

        BeanUtils.copyProperties(post.getDetail(), detailResponse);
        BeanUtils.copyProperties(post, postResponse);

        detailResponse.setProductId(post.getDetail().getId());
        postResponse.setIdPost(post.getId());

        return postResponse;
    }
}
