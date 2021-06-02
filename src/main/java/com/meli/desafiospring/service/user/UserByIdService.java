package com.meli.desafiospring.service.user;

import com.meli.desafiospring.exception.user.UserNotFoundException;
import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserByIdService {

    private final UserRepository userRepository;

    public User getUserByIdService(Integer id){
        return userRepository
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", id)) );
    }
}
