package com.mercadolivre.desafio_sring.repositories;

import com.mercadolivre.desafio_sring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Long countByFollowingUserId(Long userId);

    User findByFollowingUserId(Long userId);
}
