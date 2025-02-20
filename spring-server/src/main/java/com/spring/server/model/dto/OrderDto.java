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
public class OrderDto {
    private Long id;
    private String note, status;
    private Double totalPrice;
    private OrderStatusDto orderStatus;
    private UserAddressDto address;
    private UserDto user;
    private ShopDto shop;
    private Set<OrderItemDto> items;
    private Date createdAt, updatedAt;
    private Set<OrderStatusHistoryDto> statusHistory;
}
