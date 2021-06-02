package com.meli.desafiospring.service.user;

import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.gateway.request.UserRequest;
import com.meli.desafiospring.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreateUserService {

    private final UserRepository userRepository;

    public void createUserByType(UserRequest userRequest) {
        User seller = new User();
        seller.setUserName(userRequest.getName());
        seller.setUserType(userRequest.getType());

        userRepository.save(seller);
    }
}
