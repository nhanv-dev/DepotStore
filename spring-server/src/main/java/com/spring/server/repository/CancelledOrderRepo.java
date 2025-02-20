package com.spring.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.server.model.entity.CancelledOrder;

public interface CancelledOrderRepo extends JpaRepository<CancelledOrder, Long> {
    @Query()
    CancelledOrder findOneByOrder_Id(Long orderId);

}