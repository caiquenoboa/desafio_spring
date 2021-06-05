package com.meli.desafiospring.service.user.unfollow;

import com.meli.desafiospring.exception.user.UserNotFollowException;
import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.UserByIdService;
import com.meli.desafiospring.validator.user.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UnfollowUserService {

    private final UserByIdService userByIdService;
    private final UserValidator userValidator;
    private final UserRepository userRepository;

    public void unfollowUser(Integer userId, Integer userIdToUnfollow) {
        User userActual = this.userByIdService.getUserByIdService(userIdToUnfollow);
        User userToUnfollow = this.userByIdService.getUserByIdService(userId);

        boolean hasRelationship = userValidator.validIfHasRelationshipBetweenUsers(userIdToUnfollow, userId);

        if(!hasRelationship){
            throw new UserNotFollowException(String.format("User %s not follow user %s", userId, userIdToUnfollow));
        }

        userActual.getFollowers().remove(userToUnfollow);

        userRepository.save(userActual);
    }
}
