package com.spring.server.repository;

import com.spring.server.model.entity.RatingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingInfoRepo extends JpaRepository<RatingInfo, Long> {
}