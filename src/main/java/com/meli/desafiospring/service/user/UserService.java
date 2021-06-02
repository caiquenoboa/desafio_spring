package com.meli.desafiospring.service.user;

import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.validator.user.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserByIdService userByIdService;

    private final UserValidator userValidator;

    @PostConstruct
    public void init(){
        User user1 = new User();
        user1.setUserName("User1");

        User user2 = new User();
        user2.setUserName("User2");

        userRepository.saveAll(Arrays.asList(user1, user2));
    }

    public void followUser(Integer userId, Integer userIdToFollow) {
        if(userId == null || userIdToFollow == null)
            throw new RuntimeException("Os Parâmetros não podem ser nulos");

        User userActual = userByIdService.getUserByIdService(userId);
        User userToFollow = userByIdService.getUserByIdService(userIdToFollow);

        //Caso exista a relacao de userActual e userToFollow na terceira tabela, devolver um bad request;
        userValidator.validIfHasRelationshipBetweenUser(userIdToFollow, userId);

        userToFollow.addUserFollower(userActual);

        userRepository.save(userToFollow);

    }
}
