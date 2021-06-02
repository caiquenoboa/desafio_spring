package com.meli.desafiospring.service.user.followers;

import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetFollowersCountService {

    private final UserByIdService userByIdService;

    public UserResponse getFollowersCountOfUser(Integer userId) {
        User user = this.userByIdService.getUserByIdService(userId);

        List<User> followers = user.getFollowers();

        return new UserResponse(userId, user.getUserName(), followers.size());
    }
}
