package com.spring.server.model.mapper;

import com.spring.server.model.dto.ProductImageDto;
import com.spring.server.model.entity.Product;
import com.spring.server.model.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
@Component
public class ProductImageMapper {
    public static Set<ProductImageDto> toDto(Set<ProductImage> images) {
        Set<ProductImageDto> result = new TreeSet<>();
        if (images == null || images.isEmpty()) return result;
        for (ProductImage image : images) {
            ProductImageDto productImage = new ProductImageDto();
            productImage.setId(image.getId());
            productImage.setUrl(image.getUrl());
            result.add(productImage);
        }
        return result;
    }

    public static Set<ProductImage> toEntity(Set<ProductImageDto> images, Product product) {
        Set<ProductImage> result = new HashSet<>();
        if (images == null || images.isEmpty()) return result;
        for (ProductImageDto image : images) {
            ProductImage productImage = new ProductImage();
            productImage.setId(image.getId());
            productImage.setUrl(image.getUrl());
            result.add(productImage);
        }
        return result;
    }

}
