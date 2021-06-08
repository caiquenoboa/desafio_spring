package com.meli.desafiospring.service.post;

import com.meli.desafiospring.builder.PostResponseBuilder;
import com.meli.desafiospring.gateway.repository.PostRepository;
import com.meli.desafiospring.gateway.request.PostRequest;
import com.meli.desafiospring.gateway.response.PostResponse;
import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.model.Detail;
import com.meli.desafiospring.model.Post;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import com.meli.desafiospring.service.user.followed.GetAllFollowersService;
import com.meli.desafiospring.util.list.ListUtil;
import com.meli.desafiospring.util.list.OrderListUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserByIdService userByIdService;
    private final PostByIdsUserService postByIdsUserService;
    private final GetAllFollowersService getAllFollowersService;
    private final ListUtil<User, Integer> listUtil = new ListUtil<>();

    public void createPost(PostRequest postRequest) {
        User user = this.userByIdService.getUserByIdService(postRequest.getUserId());

        Detail detail = new Detail();
        Post post = new Post(detail, user);

        BeanUtils.copyProperties(postRequest.getDetail(), detail);
        BeanUtils.copyProperties(postRequest, post);

        postRepository.save(post);
    }

    public UserResponse getPostsOfSellersFollowed(Integer userId, String order) {
        User user = this.userByIdService.getUserByIdService(userId);

        List<User> usersFollowers = getAllFollowersService.getAllById(user.getId());
        List<Integer> usersIdsFollowed =  listUtil.map(User::getId, usersFollowers);

        List<Post> posts = postByIdsUserService.getAllPostByIdsUser(usersIdsFollowed);

        return mountResponse(posts, userId, order);
    }

    private UserResponse mountResponse(List<Post> posts, Integer userId, String order) {
        List<PostResponse> postResponseList = new ArrayList<>();

        posts.forEach(p -> postResponseList.add( PostResponseBuilder.builder(p) ));

        OrderListUtil.order(order, postResponseList, "date");

        return new UserResponse(userId, postResponseList);
    }


}
