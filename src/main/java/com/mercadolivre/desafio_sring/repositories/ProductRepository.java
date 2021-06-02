package com.mercadolivre.desafio_sring.repositories;

import com.mercadolivre.desafio_sring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { }
