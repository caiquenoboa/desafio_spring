package com.meli.desafiospring.service.user.followers;

import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import com.meli.desafiospring.util.user.OrderUserResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FollowersService {

    private final UserByIdService userByIdService;

    public UserResponse getFollowers(Integer userId, boolean isEndPointOfCount, String order){
        User user = this.userByIdService.getUserByIdService(userId);

        List<User> followers = user.getFollowers();

        if(isEndPointOfCount){
            return new UserResponse(user.getId(), user.getUserName(), followers.size());
        }

        return mountObjectOfResponse(user, followers, order);
    }

    private UserResponse mountObjectOfResponse(User user, List<User> followers, String order){
        List<UserResponse> followersResponse = followers.stream()
                                                        .map(u -> new UserResponse( u.getId(), u.getUserName() ))
                                                        .collect(Collectors.toList());

        OrderUserResponseUtil.order(followersResponse, order);

        return new UserResponse(user.getId(), user.getUserName(), followersResponse);
    }

}
