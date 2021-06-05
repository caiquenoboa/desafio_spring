package com.meli.desafiospring.gateway.repository;

import com.meli.desafiospring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<List<Post>> findAllByUserIdInAndDateGreaterThan(List<Integer> idsUser, LocalDate dateBefore15Days);

    Optional<List<Post>> findAllByUserIdAndHasPromoTrue(Integer userId);
}
