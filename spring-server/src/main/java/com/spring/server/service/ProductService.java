package com.spring.server.service;

import com.spring.server.model.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    ProductDto findOneById(Long id);

    ProductDto findOneById(Long id, Boolean isPublic, Boolean isDeleted);

    ProductDto findOneBySlug(String slug);

    Page<ProductDto> findByOrderByCreatedAt(int page, int size);

    Page<ProductDto> findByCategorySlug(int page, int size, String categorySlug);

    Page<ProductDto> findByShopId(int page, int size, Long shopId, Boolean isPublic, Boolean isDeleted);

    ProductDto save(ProductDto productDto);

    @Transactional
    ProductDto update(ProductDto productDto);

    void delete(Long id);

    Page<ProductDto> searchProducts(int page, int size, String name);

}
