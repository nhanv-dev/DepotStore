package com.spring.server.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductAttributeOptionDto implements Comparable<ProductAttributeOptionDto> {
    private Long id, attributeId;
    private String name, value, image;
    private boolean isDeleted;

    @Override
    public int compareTo(ProductAttributeOptionDto o) {
        return id.compareTo(o.getId());
    }
}
