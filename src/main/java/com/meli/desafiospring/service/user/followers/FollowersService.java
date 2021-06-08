package com.meli.desafiospring.service.user.followers;

import com.meli.desafiospring.exception.user.ClientHasNotFollowerException;
import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import com.meli.desafiospring.util.list.ListUtil;
import com.meli.desafiospring.util.list.OrderListUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowersService {

    private final UserByIdService userByIdService;
    private final ListUtil<User, UserResponse> listUtil = new ListUtil<>();

    public UserResponse getFollowers(Integer userId, boolean isEndPointOfCount, String order){
        User user = this.userByIdService.getUserByIdService(userId);

        if(user.isUserTypeClient()){
            throw new ClientHasNotFollowerException("Client cannot has followers");
        }

        List<User> followers = user.getFollowers();

        if(isEndPointOfCount){
            return new UserResponse(user.getId(), user.getUserName(), followers.size());
        }

        return mountObjectOfResponse(user, followers, order);
    }

    private UserResponse mountObjectOfResponse(User user, List<User> followers, String order){
        List<UserResponse> followersResponse = listUtil
                                                .map(u -> new UserResponse( u.getId(), u.getUserName() ), followers);

        OrderListUtil.order(order, followersResponse, "name");

        return new UserResponse(user.getId(), user.getUserName(), followersResponse);
    }

}
