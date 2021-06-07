package com.meli.desafiospring.service.user.followed;

import com.meli.desafiospring.gateway.repository.UserRepository;
import com.meli.desafiospring.model.User;
import com.meli.desafiospring.service.user.followed.abstracts.AbstractFollowerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetAllFollowersService extends AbstractFollowerService {

    public GetAllFollowersService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    protected Optional<List<User>> findInDatabaseByIdUser(Integer userId) {
        return super.userRepository.findAllFollowersByUserId(userId);
    }
}
