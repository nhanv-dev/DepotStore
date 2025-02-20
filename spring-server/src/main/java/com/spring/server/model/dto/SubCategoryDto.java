package com.spring.server.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubCategoryDto implements Comparable<SubCategoryDto> {
    private Long id;
    private String title, slug, icon;

    @Override
    public int compareTo(SubCategoryDto o) {
        return id.compareTo(o.getId());
    }
}
