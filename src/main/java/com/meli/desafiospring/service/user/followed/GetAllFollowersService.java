package com.meli.desafiospring.service.user.followed;

import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class GetAllFollowersService {

    private final UserRepository userRepository;

    public List<User> getAllById(Integer userId){
        List<User> users = new ArrayList<>();

        userRepository.findAllFollowersByUserId(userId)
                        .ifPresent(users::addAll);

        return users;
    }
}
