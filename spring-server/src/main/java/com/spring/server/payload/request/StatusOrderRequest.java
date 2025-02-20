package com.spring.server.payload.request;

import com.spring.server.model.constant.EOrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StatusOrderRequest {
    @NotBlank
    private Long orderId;

    @NotBlank
    private EOrderStatus status;

}
