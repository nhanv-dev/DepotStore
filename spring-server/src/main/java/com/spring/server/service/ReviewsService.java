package com.spring.server.service;

import org.springframework.data.domain.Page;

import com.spring.server.model.dto.ProductReviewsDto;

public interface ReviewsService {
    Page<ProductReviewsDto> findProductReviewsByUserId(int page, int size, Long userId);
    Page<ProductReviewsDto> findProductReviewsByProductId(int page, int size, Long productId);

    void evaluateProduct(ProductReviewsDto productReviews);

}
