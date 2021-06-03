package com.meli.desafiospring.validator.user;

import com.meli.desafiospring.service.user.UserByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserExistValidator {

    private final UserByIdService userByIdService;

    public void validIfUserExist(Integer userId){
        userByIdService.getUserByIdService(userId);
    }
}
