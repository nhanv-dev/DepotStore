package com.spring.server.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductReviewsDto implements Comparable<ProductReviewsDto> {
    private Long id, userId, orderItemId, productId, variantId;
    private UserDto user;
    private ProductDto product;
    private ProductVariantDto variant;
    private String content;
    private Integer rating;
    private Date createdAt, updatedAt;

    @Override
    public int compareTo(ProductReviewsDto o) {
        return id.compareTo(o.getId());
    }
}
