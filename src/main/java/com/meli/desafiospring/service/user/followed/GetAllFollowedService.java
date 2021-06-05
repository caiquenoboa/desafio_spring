package com.meli.desafiospring.service.user.followed;

import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class GetAllFollowedService {

    private final UserRepository userRepository;

    public List<User> getAllById(Integer userId){
        List<User> users = new ArrayList<>();

        userRepository.findAllFollowedByUserId(userId)
                        .ifPresent(users::addAll);

        return users;
    }
}
