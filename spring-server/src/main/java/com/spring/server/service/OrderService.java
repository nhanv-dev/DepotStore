package com.spring.server.service;


import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.server.model.constant.EOrderStatus;
import com.spring.server.model.dto.CancelledOrderDto;
import com.spring.server.model.dto.OrderDto;
import com.spring.server.model.dto.OrderStatusDto;


public interface OrderService {
    OrderDto findOneById(Long id);

    Page<OrderDto> findByShopId(Pageable pageable, Long shopId);

    Page<OrderDto> findAllByUserId(int page, int size, Long userId);

    Page<OrderDto> findAllByUserIdAndStatusId(int page, int size, Long userId, Long statusId);

    OrderDto save(OrderDto orderDto);

    OrderDto updateStatus(Long orderId, EOrderStatus status);

    Set<OrderDto> placeOrder(Set<OrderDto> orderDtos);

    void cancelOrder(CancelledOrderDto cancelledOrderDto);

    CancelledOrderDto findCancelledOrderByOrderId(Long orderId);

    Set<OrderStatusDto> findAllStatus();

    OrderStatusDto findStatusByType(EOrderStatus type);


}
