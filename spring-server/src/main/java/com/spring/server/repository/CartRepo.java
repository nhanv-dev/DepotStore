package com.spring.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.server.model.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {

    @Query()
    Cart findOneByUser_Id(Long userId);
}