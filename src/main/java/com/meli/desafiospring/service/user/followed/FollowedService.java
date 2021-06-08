package com.meli.desafiospring.service.user.followed;

import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import com.meli.desafiospring.util.list.ListUtil;
import com.meli.desafiospring.util.list.OrderListUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FollowedService {

    private final UserByIdService userByIdService;
    private final GetAllFollowedService getAllFollowedService;
    private final ListUtil<User, UserResponse> listUtil = new ListUtil<>();

    public UserResponse getAllFollowed(Integer userId, String order){
        User user = this.userByIdService.getUserByIdService(userId);

        List<User> usersFollowed = getAllFollowedService.getAllById(userId);

        List<UserResponse> usersFollowedMounted = listUtil.map(this::mountUserResponseWithFollowed, usersFollowed);

        OrderListUtil.order(order, usersFollowedMounted, "name");

        return new UserResponse(userId, user.getUserName(), null, null,
                                                                                    usersFollowedMounted, null);
    }

    private UserResponse mountUserResponseWithFollowed(User user) {
        return new UserResponse(user.getId(), user.getUserName());
    }
}
