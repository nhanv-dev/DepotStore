package com.spring.server.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DealDto implements Comparable<DealDto> {
    private Long id;
    private Double price, finalPrice, discountPercent;
    @Override
    public int compareTo(DealDto o) {
        return this.getId().compareTo(o.getId());
    }
}
