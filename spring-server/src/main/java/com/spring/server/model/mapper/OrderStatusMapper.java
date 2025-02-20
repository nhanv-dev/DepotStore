package com.spring.server.model.mapper;

import com.spring.server.model.dto.OrderStatusDto;
import com.spring.server.model.entity.OrderStatus;

import java.util.Set;
import java.util.TreeSet;

public class OrderStatusMapper {

    public static OrderStatusDto toDto(OrderStatus orderStatus) {
        OrderStatusDto result = new OrderStatusDto();
        result.setId(orderStatus.getId());
        result.setTitle(orderStatus.getTitle());
        result.setStatus(orderStatus.getStatus());
        result.setDescription(orderStatus.getDescription());
        result.setLabelConfirm(orderStatus.getLabelConfirm());
        result.setLabelCreatedAt(orderStatus.getLabelCreatedAt());
        return result;
    }

    public static Set<OrderStatusDto> toDto(Set<OrderStatus> orderStatus) {
        Set<OrderStatusDto> result = new TreeSet<>();
        for (OrderStatus status : orderStatus)
            result.add(toDto(status));

        return result;
    }

    public static OrderStatus toEntity(OrderStatusDto orderStatusDto) {
        OrderStatus result = new OrderStatus();
        result.setId(orderStatusDto.getId());
        result.setStatus(orderStatusDto.getStatus());
        result.setDescription(orderStatusDto.getDescription());
        return result;
    }

}
