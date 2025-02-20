package com.spring.server.model.mapper;

import com.spring.server.model.dto.OrderStatusHistoryDto;
import com.spring.server.model.entity.OrderStatusHistory;

import java.util.Set;
import java.util.TreeSet;

public class OrderStatusHistoryMapper {
    public static OrderStatusHistoryDto toDto(OrderStatusHistory history) {
        OrderStatusHistoryDto result = new OrderStatusHistoryDto();
        result.setId(history.getId());
        result.setCreatedAt(history.getCreatedAt());
        result.setUpdatedAt(history.getUpdatedAt());
        result.setOrderStatus(OrderStatusMapper.toDto(history.getOrderStatus()));
        return result;
    }

    public static Set<OrderStatusHistoryDto> toDto(Set<OrderStatusHistory> histories) {
        Set<OrderStatusHistoryDto> result = new TreeSet<>();
        for (OrderStatusHistory history : histories) {
            result.add(toDto(history));
        }

        return result;
    }
}
