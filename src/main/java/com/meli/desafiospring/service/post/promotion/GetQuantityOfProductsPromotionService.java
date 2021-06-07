package com.meli.desafiospring.service.post.promotion;

import com.meli.desafiospring.builder.PostResponseBuilder;
import com.meli.desafiospring.gateway.repository.PostRepository;
import com.meli.desafiospring.gateway.response.PostPromoResponse;
import com.meli.desafiospring.gateway.response.PostResponse;
import com.meli.desafiospring.model.Post;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import com.meli.desafiospring.util.list.ListUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetQuantityOfProductsPromotionService {

    private final PostRepository postRepository;
    private final UserByIdService userByIdService;
    private final ListUtil<Post, PostResponse> listUtil = new ListUtil<>();

    public PostPromoResponse getByUserId(Integer userId, boolean isCountEndPoint) {
        User user = this.userByIdService.getUserByIdService(userId);

        List<Post> posts = new ArrayList<>();
        postRepository.findAllByUserIdAndHasPromoTrue(userId).ifPresent(posts::addAll);

        List<PostResponse> postResponse = listUtil.map(PostResponseBuilder::builder, posts);

        if(isCountEndPoint){
            return new PostPromoResponse(userId, user.getUserName(), postResponse.size());
        }

        return new PostPromoResponse(userId, user.getUserName(), postResponse);
    }
}
