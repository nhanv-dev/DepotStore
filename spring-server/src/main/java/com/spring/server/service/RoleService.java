package com.spring.server.service;

import com.spring.server.model.constant.ERole;
import com.spring.server.model.entity.Role;

public interface RoleService {

    Role findOneByType(ERole type);

}
