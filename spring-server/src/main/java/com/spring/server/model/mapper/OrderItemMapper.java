package com.spring.server.model.mapper;

import com.spring.server.model.dto.OrderItemDto;
import com.spring.server.model.entity.OrderItem;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class OrderItemMapper {

    public static OrderItemDto toDto(OrderItem orderItem) {
        OrderItemDto result = new OrderItemDto();
        result.setId(orderItem.getId());
        result.setPrice(orderItem.getPrice());
        result.setFinalPrice(orderItem.getFinalPrice());
        result.setDiscountPercent(orderItem.getDiscountPercent());
        result.setQuantity(orderItem.getQuantity());
        result.setProduct(ProductMinimalMapper.toDto(orderItem.getProduct()));
        result.setVariant(ProductVariantMapper.toDto(orderItem.getVariant()));
        result.setIsEvaluated(orderItem.getIsEvaluated());

        return result;
    }

    public static Set<OrderItemDto> toDto(Set<OrderItem> orderItems) {
        Set<OrderItemDto> result = new TreeSet<>();
        for (OrderItem item : orderItems) {
            result.add(toDto(item));
        }
        return result;
    }

    public static OrderItem toEntity(OrderItemDto orderItemDto) {
        OrderItem result = new OrderItem();
        result.setPrice(orderItemDto.getPrice());
        result.setFinalPrice(orderItemDto.getFinalPrice());
        result.setDiscountPercent(orderItemDto.getDiscountPercent());
        result.setQuantity(orderItemDto.getQuantity());
        result.setProduct(ProductMapper.toEntity(orderItemDto.getProduct()));
        result.setVariant(ProductVariantMapper.toEntity(orderItemDto.getVariant(), result.getProduct()));
        result.setIsEvaluated(orderItemDto.getIsEvaluated());

        return result;
    }

    public static Set<OrderItem> toEntities(Set<OrderItemDto> orderItemDtos) {
        Set<OrderItem> result = new HashSet<>();
        for (OrderItemDto item : orderItemDtos) {
            result.add(toEntity(item));
        }
        return result;
    }
}
