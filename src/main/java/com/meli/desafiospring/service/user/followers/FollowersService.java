package com.meli.desafiospring.service.user.followers;

import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FollowersService {

    private final UserByIdService userByIdService;

    public UserResponse getFollowers(Integer userId, boolean isEndPointOfCount){
        User user = this.userByIdService.getUserByIdService(userId);

        List<User> followers = user.getFollowers();

        if(isEndPointOfCount){
            return new UserResponse(user.getId(), user.getUserName(), followers.size());
        }

        return mountObjectOfResponse(user, followers);
    }

    private UserResponse mountObjectOfResponse(User user, List<User> followers){
        List<UserResponse> followersResponse = followers.stream()
                                                        .map(u -> new UserResponse( u.getId(), u.getUserName() ))
                                                        .collect(Collectors.toList());

        return new UserResponse(user.getId(), user.getUserName(), followersResponse);
    }

}
