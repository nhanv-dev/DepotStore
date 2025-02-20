package com.spring.server.model.mapper;

import com.spring.server.model.dto.UserDto;
import com.spring.server.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMinimalMapper {
    public static UserDto toDto(User user) {
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail());

        return result;
    }


}
