package com.spring.server.repository;

import com.spring.server.model.entity.ReturnPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnPolicyRepo extends JpaRepository<ReturnPolicy, Long> {
}