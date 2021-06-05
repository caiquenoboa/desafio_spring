package com.meli.desafiospring.service.user.followed;

import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import com.meli.desafiospring.util.user.OrderUserResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FollowedService {

    private final UserByIdService userByIdService;
    private final GetAllFollowedService getAllFollowedService;

    public UserResponse getAllFollowed(Integer userId, String order){
        User user = this.userByIdService.getUserByIdService(userId);

        List<User> usersFollowed = getAllFollowedService.getAllById(userId);

        List<UserResponse> usersFollowedMounted = usersFollowed.stream()
                                                                .map(this::mountUserResponseWithFollowed)
                                                                .collect(Collectors.toList());

        OrderUserResponseUtil.order(usersFollowedMounted, order);

        return new UserResponse(userId, user.getUserName(), null, null,
                                                                                    usersFollowedMounted, null);
    }

    private UserResponse mountUserResponseWithFollowed(User user) {
        return new UserResponse(user.getId(), user.getUserName());
    }
}
