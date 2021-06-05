package com.meli.desafiospring.service.post.promotion;

import com.meli.desafiospring.builder.PostResponseBuilder;
import com.meli.desafiospring.gateway.repository.PostRepository;
import com.meli.desafiospring.gateway.response.PostPromoResponse;
import com.meli.desafiospring.gateway.response.PostResponse;
import com.meli.desafiospring.model.Post;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetQuantityOfProductsPromotionService {

    private final PostRepository postRepository;
    private final UserByIdService userByIdService;

    public PostPromoResponse getByUserId(Integer userId, boolean isCountEndPoint) {
        User user = this.userByIdService.getUserByIdService(userId);

        List<Post> posts = new ArrayList<>();
        postRepository.findAllByUserIdAndHasPromoTrue(userId).ifPresent(posts::addAll);

        List<PostResponse> postResponse = posts.stream()
                                                .map(PostResponseBuilder::builder)
                                                .collect(Collectors.toList());

        if(isCountEndPoint){
            return new PostPromoResponse(userId, user.getUserName(), postResponse.size());
        }

        return new PostPromoResponse(userId, user.getUserName(), postResponse);
    }
}
