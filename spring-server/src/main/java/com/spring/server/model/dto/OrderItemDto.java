package com.spring.server.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto implements Comparable<OrderItemDto> {
    private Long id, userId, orderId, cartItemId;
    private ProductDto product;
    private ProductVariantDto variant;
    private Integer quantity;
    private Double price, finalPrice, discountPercent;
    private Boolean isEvaluated;

    @Override
    public int compareTo(OrderItemDto o) {
        return id.compareTo(o.getId());
    }
}
