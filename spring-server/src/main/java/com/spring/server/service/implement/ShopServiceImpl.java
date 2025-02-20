package com.spring.server.service.implement;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.server.model.constant.ERole;
import com.spring.server.model.dto.ShopDto;
import com.spring.server.model.entity.RatingInfo;
import com.spring.server.model.entity.Role;
import com.spring.server.model.entity.Shop;
import com.spring.server.model.entity.User;
import com.spring.server.model.mapper.ShopMapper;
import com.spring.server.repository.RatingInfoRepo;
import com.spring.server.repository.RoleRepo;
import com.spring.server.repository.ShopRepo;
import com.spring.server.repository.UserRepo;
import com.spring.server.service.ShopService;
import com.spring.server.util.SlugGenerator;


@Component
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopRepo shopRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    RatingInfoRepo ratingInfoRepo;

    @Override
    public ShopDto findOneById(Long id) {
        return ShopMapper.toDto(shopRepo.findOneById(id));
    }

    @Override
    public ShopDto findOneByUserId(Long id) {
        return ShopMapper.toDto(shopRepo.findOneByUserId(id));
    }

    @Override
    public ShopDto findOneBySlug(String slug) {
        return ShopMapper.toDto(shopRepo.findOneBySlug(slug));
    }

    @Override
    public Page<ShopDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Shop> shops = shopRepo.findAll(pageable);
        return ShopMapper.toDto(shops);
    }

    @Override
    @Transactional
    public ShopDto save(Shop shop) {
        RatingInfo ratingInfo = ratingInfoRepo.save(new RatingInfo());
        shop.setRatingInfo(ratingInfo);
        Shop savedShop = shopRepo.save(shop);
        savedShop.setSlug(SlugGenerator.toSlug(savedShop.getShopName() + "-" + savedShop.getId()));
        savedShop = shopRepo.save(savedShop);

        User user = shop.getUser();
        Set<Role> roles = user.getRoles();
        roles.add(roleRepo.findOneByType(ERole.ROLE_SHOP));
        user.setRoles(roles);
        userRepo.save(user);
        return ShopMapper.toDto(savedShop);
    }

    @Override
    @Transactional
    public ShopDto update(ShopDto shopDto) {
        User user = userRepo.findOneById(shopDto.getUserId());
        Shop shop = ShopMapper.toEntity(shopDto);
        shop.setUser(user);
        shop.setSlug(SlugGenerator.toSlug(shop.getShopName() + "-" + shop.getId()));
        shopRepo.save(shop);
        shop = shopRepo.findOneById(shop.getId());
        return ShopMapper.toDto(shop);
    }

    @Override
    public Page<ShopDto> searchShop(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Shop> shops = shopRepo.searchShopByName(pageable, name);
        if (shops == null) return null;
        return ShopMapper.toDto(shops);
    }


}
