package com.spring.server.repository;

import com.spring.server.model.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepo extends JpaRepository<Deal, Long> {
}