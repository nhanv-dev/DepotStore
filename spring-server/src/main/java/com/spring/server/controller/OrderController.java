package com.spring.server.controller;

import com.spring.server.model.constant.EOrderStatus;
import com.spring.server.model.dto.CancelledOrderDto;
import com.spring.server.model.dto.OrderDto;
import com.spring.server.model.dto.OrderStatusDto;
import com.spring.server.model.entity.User;
import com.spring.server.model.mapper.UserMapper;
import com.spring.server.payload.request.StatusOrderRequest;
import com.spring.server.service.OrderService;
import com.spring.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @GetMapping("/order-status")
    public ResponseEntity<?> getOrderStatus() {
        Set<OrderStatusDto> status = orderService.findAllStatus();
        return ResponseEntity.ok(status);
    }

    @GetMapping("/users/{userId}/orders")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getOrdersByUserId(Authentication authentication, @PathVariable Long userId, @RequestParam(required = false) Long status, @RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer size) {
        User user = userService.findOneByEmail(authentication.getName());
        if (user == null || !Objects.equals(user.getId(), userId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Don't have permission to access");
        Page<OrderDto> orders;
        if (status == null) {
            orders = orderService.findAllByUserId(page, size, userId);
        } else {
            orders = orderService.findAllByUserIdAndStatusId(page, size, userId, status);
        }
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/users/{userId}/orders/place-order")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> placeOrder(Authentication authentication, @PathVariable Long userId, @RequestBody Set<OrderDto> orders) {
        User user = userService.findOneByEmail(authentication.getName());
        if (user == null || !Objects.equals(user.getId(), userId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Don't have permission to access");
        OrderStatusDto status = orderService.findStatusByType(EOrderStatus.PENDING);
        orders.forEach(orderDto -> {
            orderDto.setOrderStatus(status);
            orderDto.setUser(UserMapper.toDto(user));
        });
        Set<OrderDto> orderDtos = orderService.placeOrder(orders);
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping("/users/{userId}/orders/{orderId}/cancel-order")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> cancelOrder(Authentication authentication, @PathVariable Long userId, @PathVariable Long orderId, @RequestBody CancelledOrderDto cancelledOrderDto) {
        User user = userService.findOneByEmail(authentication.getName());
        if (user == null || !Objects.equals(user.getId(), userId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Don't have permission to access");
        if (!Objects.equals(cancelledOrderDto.getOrderId(), orderId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not match order's id");
        orderService.cancelOrder(cancelledOrderDto);
        OrderDto order = orderService.findOneById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orders/{orderId}/cancel-order")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SHOP')")
    public ResponseEntity<?> getCancelledOrderById(@PathVariable Long orderId) {
        CancelledOrderDto order = orderService.findCancelledOrderByOrderId(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/shops/orders/{orderId}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        OrderDto order = orderService.findOneById(orderId);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/shops/orders/{orderId}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody StatusOrderRequest request) {
        if (!Objects.equals(orderId, request.getOrderId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        OrderDto order = orderService.updateStatus(request.getOrderId(), request.getStatus());
        return ResponseEntity.ok(order);
    }

}

