package com.meli.desafiospring.gateway.repository;

import com.meli.desafiospring.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
}
