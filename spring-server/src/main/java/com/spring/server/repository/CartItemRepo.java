package com.spring.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.server.model.entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    @Query()
    void deleteById(Long id);

    @Query("UPDATE CartItem c SET c.quantity=:quantity WHERE c.id=:id")
    @Modifying
    void updateQuantity(Long id, Integer quantity);
}