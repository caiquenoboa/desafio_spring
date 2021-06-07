package com.meli.desafiospring.service.user.followed;

import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public abstract class AbstractFollowerService {

    protected final UserRepository userRepository;

    public List<User> getAllById(Integer userId){
        List<User> users = new ArrayList<>();

        findInDatabaseByIdUser(userId).ifPresent(users::addAll);

        return users;
    }

    protected abstract Optional<List<User>> findInDatabaseByIdUser(Integer userId);
}
