package com.meli.desafiospring.validator.user;

import com.meli.desafiospring.gateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserValidator {

    private final UserRepository userRepository;

    public void validIfHasRelationshipBetweenUser(Integer userId, Integer userIdToFollow){
        boolean hasRelationship = userRepository.findByUserIdAndUserIdFollower(userId, userIdToFollow).isPresent();

        if(hasRelationship){
            throw new RuntimeException(String.format("Vendedor %s já segue o vendedor %s ", userIdToFollow, userId));
        }
    }
}
