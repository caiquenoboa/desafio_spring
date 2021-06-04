package com.mercadolivre.desafio_sring.repositories;

import com.mercadolivre.desafio_sring.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Long countByFollowingUserId(Long userId);

    List<User> findByFollowingUserId(Long userId, Sort sort);

    List<User> findByFollowersUserId(Long userId, Sort sort);
}
