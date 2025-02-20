package com.spring.server.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String shortDescription;
    private String keywords;
    private Integer quantity;
    private Integer orderCount;
    private Long shopId;
    private CategoryDto category;
    private SubCategoryDto subCategory;
    private DealDto deal;
    private ShopDto shop;
    private RatingInfoDto ratingInfo;
    private Boolean isDeleted = false, isPublic = true;
    private Date createdAt, updatedAt;
    private Set<ReturnPolicyDto> returnPolicies;
    private Set<ProductImageDto> images;
    private Set<ProductAttributeDto> attributes;
    private Set<ProductVariantDto> variants;

}
