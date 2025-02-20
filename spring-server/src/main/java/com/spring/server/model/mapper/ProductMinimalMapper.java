package com.spring.server.model.mapper;

import com.spring.server.model.dto.ProductDto;
import com.spring.server.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMinimalMapper {
    public static ProductDto toDto(Product product) {
        ProductDto result = new ProductDto();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setSlug(product.getSlug());
        result.setOrderCount(product.getOrderCount());
        result.setQuantity(product.getQuantity());
        result.setKeywords(product.getKeywords());
        result.setIsPublic(product.getIsPublic());
        result.setIsDeleted(product.getIsDeleted());
        result.setCreatedAt(product.getCreatedAt());
        result.setUpdatedAt(product.getUpdatedAt());
        result.setShopId(product.getShop().getId());
        result.setDeal(DealMapper.toDto(product.getDeal()));
        result.setRatingInfo(RatingInfoMapper.toDto(product.getRatingInfo()));
        result.setImages(ProductImageMapper.toDto(product.getImages()));

        return result;
    }

}
