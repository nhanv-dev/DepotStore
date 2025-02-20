package com.spring.server.repository;

import com.spring.server.model.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductVariantRepo extends JpaRepository<ProductVariant, Long> {
    @Query()
    ProductVariant findOneById(Long id);

}