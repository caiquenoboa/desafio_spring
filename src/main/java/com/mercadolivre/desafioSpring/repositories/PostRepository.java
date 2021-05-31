package com.mercadolivre.desafioSpring.repositories;

import com.mercadolivre.desafioSpring.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
