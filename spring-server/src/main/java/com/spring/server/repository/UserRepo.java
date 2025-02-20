package com.spring.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.server.model.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query()
    User findOneById(Long id);

    @Query()
    User findOneByEmail(String email);

    @Query()
    Boolean existsByEmail(String email);
}