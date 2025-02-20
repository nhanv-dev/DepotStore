package com.spring.server.repository;

import com.spring.server.model.entity.ProductReviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductReviewsRepo extends JpaRepository<ProductReviews, Long> {
    @Query()
    Page<ProductReviews> findByUser_Id(Pageable pageable, Long userId);

    @Query()
    Page<ProductReviews> findByProduct_Id(Pageable pageable, Long productId);
}