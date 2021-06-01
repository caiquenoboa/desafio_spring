package com.mercadolivre.desafioSpring.repositories;

import com.mercadolivre.desafioSpring.dtos.ResponseDTO;
import com.mercadolivre.desafioSpring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    ResponseDTO countByFollowersUserId(Integer userId);
}
