package com.spring.server.model.mapper;

import com.spring.server.model.dto.ProductDto;
import com.spring.server.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductMapper {
    public static ProductDto toDto(Product product) {
        ProductDto result = ProductMinimalMapper.toDto(product);
        result.setCategory(CategoryMapper.toDtoWithoutSub(product.getCategory()));
        result.setSubCategory(SubCategoryMapper.toDto(product.getSubCategory()));
        result.setShop(ShopMapper.toDto(product.getShop()));

        return result;
    }

    public static Page<ProductDto> toDto(Page<Product> products) {
        return products.map(new Function<Product, ProductDto>() {
            @Override
            public ProductDto apply(Product product) {
                return toDto(product);
            }
        });
    }


    public static Product toEntity(ProductDto product) {
        Product result = new Product();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setSlug(product.getSlug());
        result.setOrderCount(product.getOrderCount());
        result.setQuantity(product.getQuantity());
        result.setKeywords(product.getKeywords());
        result.setIsPublic(product.getIsPublic());
        result.setIsDeleted(product.getIsDeleted());

        result.setCategory(CategoryMapper.toEntity(product.getCategory()));
        result.setSubCategory(SubCategoryMapper.toEntity(product.getSubCategory()));
        result.setShop(ShopMapper.toEntity(product.getShop()));
        result.setRatingInfo(RatingInfoMapper.toEntity(product.getRatingInfo()));
        result.setDeal(DealMapper.toEntity(product.getDeal()));
        result.setImages(ProductImageMapper.toEntity(product.getImages(), result));

        return result;
    }

}
