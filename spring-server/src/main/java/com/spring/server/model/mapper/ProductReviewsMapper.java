package com.spring.server.model.mapper;

import com.spring.server.model.dto.ProductReviewsDto;
import com.spring.server.model.entity.ProductReviews;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductReviewsMapper {
    public static ProductReviewsDto toDto(ProductReviews productReviews) {
        ProductReviewsDto result = new ProductReviewsDto();
        result.setId(productReviews.getId());
        result.setUser(UserMinimalMapper.toDto(productReviews.getUser()));
        result.setProduct(ProductMinimalMapper.toDto(productReviews.getProduct()));
        result.setVariant(ProductVariantMapper.toDto(productReviews.getVariant()));
        result.setContent(productReviews.getContent());
        result.setRating(productReviews.getRating());
        result.setCreatedAt(productReviews.getCreatedAt());
        result.setVariant(ProductVariantMapper.toDto(productReviews.getVariant()));
        return result;
    }

    public static Page<ProductReviewsDto> toDto(Page<ProductReviews> productReviews) {
        return productReviews.map(new Function<ProductReviews, ProductReviewsDto>() {
            @Override
            public ProductReviewsDto apply(ProductReviews productReview) {
                return toDto(productReview);
            }
        });
    }


}
