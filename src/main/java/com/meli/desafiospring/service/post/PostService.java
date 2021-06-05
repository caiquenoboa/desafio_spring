package com.meli.desafiospring.service.post;

import com.meli.desafiospring.gateway.repository.PostRepository;
import com.meli.desafiospring.gateway.request.PostRequest;
import com.meli.desafiospring.gateway.response.DetailResponse;
import com.meli.desafiospring.gateway.response.PostResponse;
import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.model.Detail;
import com.meli.desafiospring.model.Post;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import com.meli.desafiospring.service.user.followed.GetAllFollowedService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserByIdService userByIdService;
    private final GetAllFollowedService getAllFollowedService;
    private final PostByIdsUserService postByIdsUserService;

    public void createPost(PostRequest postRequest) {
        User user = this.userByIdService.getUserByIdService(postRequest.getUserId());

        Detail detail = new Detail();
        Post post = new Post(detail, user);

        BeanUtils.copyProperties(postRequest.getDetail(), detail);
        BeanUtils.copyProperties(postRequest, post);

        postRepository.save(post);
    }

    public UserResponse getPostsOfSellersFollowed(Integer userId) {
        User user = this.userByIdService.getUserByIdService(userId);

        System.out.println(user.toString());

        List<Integer> usersIdsFollowed = getAllFollowedService.getAllById(user.getId())
                                                                .stream()
                                                                .map(User::getId)
                                                                .collect(Collectors.toList());

        List<Post> posts = postByIdsUserService.getAllPostByIdsUser(usersIdsFollowed);

        return mountResponse(posts, userId);
    }

    private UserResponse mountResponse(List<Post> posts, Integer userId) {
        List<PostResponse> postResponseList = new ArrayList<>();

        posts.forEach(p -> {
            DetailResponse detailResponse = new DetailResponse();
            PostResponse postResponse = new PostResponse(detailResponse);

            BeanUtils.copyProperties(p.getDetail(), detailResponse);
            BeanUtils.copyProperties(p, postResponse);

            detailResponse.setProductId(p.getDetail().getId());
            postResponse.setIdPost(p.getId());

            postResponseList.add(postResponse);

        });

        return new UserResponse(userId, postResponseList);
    }
}
