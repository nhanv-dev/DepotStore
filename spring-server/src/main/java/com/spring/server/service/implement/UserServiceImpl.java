package com.spring.server.service.implement;

import com.spring.server.model.dto.UserAddressDto;
import com.spring.server.model.dto.UserDto;
import com.spring.server.model.entity.User;
import com.spring.server.model.entity.UserAddress;
import com.spring.server.model.mapper.UserAddressMapper;
import com.spring.server.model.mapper.UserMapper;
import com.spring.server.repository.UserAddressRepo;
import com.spring.server.repository.UserRepo;
import com.spring.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserAddressRepo userAddressRepo;

    @Override
    public User findById(Long id) {
        Optional<User> list = userRepo.findById(id);
        return list.get();
    }

    @Override
    public UserDto findOneById(Long id) {
        return UserMapper.toDto(userRepo.findById(id).get());
    }


    @Override
    public User findOneByEmail(String email) {
        return userRepo.findOneByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }


    @Override
    @Transactional
    public UserDto save(User user) {
        User userDto = userRepo.save(user);
        return UserMapper.toDto(userDto);
    }

    @Override
    @Transactional
    public void update(User currentUser) {
        userRepo.save(currentUser);
    }

    @Override
    public UserAddressDto findAddressById(Long id) {
        UserAddress address = userAddressRepo.findOneById(id);
        return UserAddressMapper.toDto(address);
    }

    @Override
    public Set<UserAddressDto> findAddressByUserId(Long userId) {
        List<UserAddress> list = userAddressRepo.findAllByUser_IdAndIsDeleted(userId, false);
        return UserAddressMapper.toDto(list);
    }

    @Override
    @Transactional
    public UserAddressDto saveAddress(UserAddressDto userAddressDto) {
        User user = userRepo.findOneById(userAddressDto.getUserId());
        UserAddress address = UserAddressMapper.toEntity(userAddressDto);
        address.setUser(user);
        address = userAddressRepo.save(address);
        return UserAddressMapper.toDto(address);
    }

    @Override
    @Transactional
    public UserAddressDto updateAddress(UserAddressDto userAddressDto) {
        User user = userRepo.findOneById(userAddressDto.getUserId());
        UserAddress address = UserAddressMapper.toEntity(userAddressDto);
        address.setUser(user);
        if (address.getIsDefault() != null && address.getIsDefault()) {
            userAddressRepo.updateDefaultStatus(user.getId());
        }
        address = userAddressRepo.save(address);
        return UserAddressMapper.toDto(address);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        userAddressRepo.deleteById(id);
    }


}
