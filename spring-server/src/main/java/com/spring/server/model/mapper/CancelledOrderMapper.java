package com.spring.server.model.mapper;

import com.spring.server.model.dto.CancelledOrderDto;
import com.spring.server.model.entity.CancelledOrder;

public class CancelledOrderMapper {

    public static CancelledOrderDto toDto(CancelledOrder cancelledOrder) {
        CancelledOrderDto result = new CancelledOrderDto();
        result.setId(cancelledOrder.getId());
        result.setOrder(OrderMapper.toDto(cancelledOrder.getOrder()));
        result.setNote(cancelledOrder.getNote());
        result.setCreatedAt(cancelledOrder.getCreatedAt());
        result.setUpdatedAt(cancelledOrder.getUpdatedAt());
        return result;
    }

    public static CancelledOrder toEntity(CancelledOrderDto cancelledOrder) {
        CancelledOrder result = new CancelledOrder();
        result.setId(cancelledOrder.getId());
        result.setNote(cancelledOrder.getNote());
        result.setCreatedAt(cancelledOrder.getCreatedAt());
        result.setUpdatedAt(cancelledOrder.getUpdatedAt());
        return result;
    }

}
