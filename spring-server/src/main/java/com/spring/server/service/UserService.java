package com.spring.server.service;

import java.util.Set;

import com.spring.server.model.dto.UserAddressDto;
import com.spring.server.model.dto.UserDto;
import com.spring.server.model.entity.User;

public interface UserService {
    User findById(Long id);

    User findOneByEmail(String email);

    UserDto findOneById(Long id);

    Boolean existsByEmail(String email);

    UserDto save(User user);

    void update(User currentUser);

    UserAddressDto findAddressById(Long id);

    Set<UserAddressDto> findAddressByUserId(Long userId);

    UserAddressDto saveAddress(UserAddressDto userAddressDto);

    UserAddressDto updateAddress(UserAddressDto userAddressDto);

    void deleteAddress(Long id);

}
