package com.spring.server.repository;

import com.spring.server.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
    @Query()
    OrderItem findOneById(Long id);
}