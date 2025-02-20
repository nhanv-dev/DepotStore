package com.spring.server.service;

import org.springframework.data.domain.Page;

import com.spring.server.model.dto.ShopDto;
import com.spring.server.model.entity.Shop;

public interface ShopService {

    ShopDto findOneById(Long id);

    ShopDto findOneByUserId(Long id);

    ShopDto findOneBySlug(String slug);

    Page<ShopDto> findAll(int page, int size);

    ShopDto save(Shop shop);

    Page<ShopDto> searchShop(int page, int size, String name);

    ShopDto update(ShopDto shopDto);

}
