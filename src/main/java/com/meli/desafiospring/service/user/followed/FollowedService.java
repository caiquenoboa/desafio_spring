package com.meli.desafiospring.service.user.followed;

import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.gateway.response.UserResponse;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FollowedService {

    private final UserByIdService userByIdService;
    private final UserRepository userRepository;

    public UserResponse getAllFollowed(Integer userId){
        User user = this.userByIdService.getUserByIdService(userId);

        Optional<List<User>> optionalUsers = userRepository.findAllFollowedByUserId(userId);

        boolean hasUsersFollowed = optionalUsers.isPresent();

        if(!hasUsersFollowed){
            return new UserResponse(userId, user.getUserName(), null, null, new ArrayList<>());
        }

        List<UserResponse> usersFollowed = optionalUsers.get()
                .stream().map(this::mountUserResponseWithFollowed)
                .collect(Collectors.toList());

        return new UserResponse(userId, user.getUserName(), null, null, usersFollowed);

    }

    private UserResponse mountUserResponseWithFollowed(User user) {
        return new UserResponse(user.getId(), user.getUserName());
    }
}
