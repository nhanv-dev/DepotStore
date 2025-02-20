package com.spring.server.repository;

import com.spring.server.model.constant.ERole;
import com.spring.server.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findOneByType(ERole type);
}