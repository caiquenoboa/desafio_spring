package com.meli.desafiospring.service.user;

import com.meli.desafiospring.exception.user.ClientCannotFollowSellerException;
import com.meli.desafiospring.exception.user.RelationshipAlreadyExistException;
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
        if(userId.equals(userIdToFollow)){
            throw new IllegalArgumentException("The user cannot follow himself");
        }

        User userActual = userByIdService.getUserByIdService(userId);
        User userToFollow = userByIdService.getUserByIdService(userIdToFollow);

        if(userActual.isUserTypeSeller() && userToFollow.isUserTypeClient()){
            throw new ClientCannotFollowSellerException("Seller cannot follow Client");
        }

        boolean hasRelationship = userValidator.validIfHasRelationshipBetweenUsers(userIdToFollow, userId);

        if(hasRelationship){
            throw new RelationshipAlreadyExistException(
                    String.format("User %s already follow user %s ", userId, userIdToFollow));
        }

        userToFollow.addUserFollower(userActual);

        userRepository.save(userToFollow);

    }
}
