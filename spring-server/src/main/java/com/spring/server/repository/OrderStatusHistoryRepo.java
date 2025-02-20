package com.spring.server.repository;

import com.spring.server.model.entity.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusHistoryRepo extends JpaRepository<OrderStatusHistory, Long> {


}