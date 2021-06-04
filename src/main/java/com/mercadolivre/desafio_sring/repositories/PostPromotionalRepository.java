package com.mercadolivre.desafio_sring.repositories;

import com.mercadolivre.desafio_sring.models.PostPromotional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostPromotionalRepository extends JpaRepository<PostPromotional, Long> {
    Long countByUserUserId(Long userId);

    List<PostPromotional> findByUserUserId(Long userId);
}
