package com.meli.desafiospring.validator.user;

import com.meli.desafiospring.gateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserValidator {

    private final UserRepository userRepository;

    public boolean validIfHasRelationshipBetweenUsers(Integer userId, Integer userIdToFollow){
        return userRepository.findByUserIdAndUserIdFollower(userId, userIdToFollow).isPresent();
    }
}
