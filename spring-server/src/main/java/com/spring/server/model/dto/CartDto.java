package com.spring.server.model.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDto implements Comparable<CartDto> {
    private Long id;
    private Long userId;
    private Set<CartItemDto> items;
    @Override
    public int compareTo(CartDto o) {
        return id.compareTo(o.getId());
    }
}
