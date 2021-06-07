package com.mercadolivre.desafioSpring.repositories;

import com.mercadolivre.desafioSpring.models.Seller;
import com.mercadolivre.desafioSpring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    User findByUserName(String userName);
}