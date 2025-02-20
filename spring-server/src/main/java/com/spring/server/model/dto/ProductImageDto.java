package com.spring.server.model.dto;


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
public class ProductImageDto implements Comparable<ProductImageDto> {
    private Long id;
    private String url;
    @Override
    public int compareTo(ProductImageDto other) {
        return this.getId().compareTo(other.getId());
    }
}
