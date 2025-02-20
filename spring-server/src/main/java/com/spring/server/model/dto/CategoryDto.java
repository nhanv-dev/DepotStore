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
public class CategoryDto implements Comparable<CategoryDto> {
    private Long id;
    private String title, slug, icon;
    private Set<SubCategoryDto> subCategories;
    @Override
    public int compareTo(CategoryDto o) {
        return id.compareTo(o.getId());
    }
}
