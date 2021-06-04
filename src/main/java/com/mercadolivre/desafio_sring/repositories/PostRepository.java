package com.mercadolivre.desafio_sring.repositories;

import com.mercadolivre.desafio_sring.models.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserFollowersUserIdAndDateBetween(Long userId, LocalDate dateStart, LocalDate dateEnd, Sort sort);
}
