package com.meli.desafiospring.service.post;

import com.meli.desafiospring.gateway.repository.PostRepository;
import com.meli.desafiospring.gateway.request.PostRequest;
import com.meli.desafiospring.model.Detail;
import com.meli.desafiospring.model.Post;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserByIdService userByIdService;

    public void createPost(PostRequest postRequest) {
        User user = this.userByIdService.getUserByIdService(postRequest.getUserId());

        Detail detail = new Detail();
        Post post = new Post(detail, user);

        BeanUtils.copyProperties(postRequest.getDetail(), detail);
        BeanUtils.copyProperties(postRequest, post);

        postRepository.save(post);
    }
}
