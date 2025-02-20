package com.spring.server.service.implement;

import com.spring.server.model.constant.ERole;
import com.spring.server.model.entity.Role;
import com.spring.server.repository.RoleRepo;
import com.spring.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findOneByType(ERole type) {
        return roleRepo.findOneByType(type);
    }
}
