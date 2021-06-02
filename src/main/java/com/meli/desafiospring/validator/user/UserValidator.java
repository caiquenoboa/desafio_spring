package com.meli.desafiospring.validator.user;

import com.meli.desafiospring.exception.user.RelationshipAlreadyExistException;
import com.meli.desafiospring.gateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserValidator {

    private final UserRepository userRepository;

    public void validIfHasRelationshipBetweenUsers(Integer userId, Integer userIdToFollow){
        boolean hasRelationship = userRepository.findByUserIdAndUserIdFollower(userId, userIdToFollow).isPresent();

        if(hasRelationship){
            throw new RelationshipAlreadyExistException(
                        String.format("Usuário %s já segue o usuário %s ", userIdToFollow, userId));
        }
    }
}
