package com.spring.server.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingInfoDto {
    private Long id;
    private Integer star1, star2, star3, star4, star5;
    private Integer totalRating;
    private Double avgRating;
}
