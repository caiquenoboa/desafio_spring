package com.meli.desafiospring.service.user;

import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.validator.user.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserByIdService userByIdService;
    private final UserValidator userValidator;

    public void followUser(Integer userId, Integer userIdToFollow) {

        User userActual = userByIdService.getUserByIdService(userId);
        User userToFollow = userByIdService.getUserByIdService(userIdToFollow);

        userValidator.validIfHasRelationshipBetweenUser(userIdToFollow, userId);

        userToFollow.addUserFollower(userActual);

        userRepository.save(userToFollow);

    }
}
