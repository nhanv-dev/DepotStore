package com.spring.server.model.mapper;

import com.spring.server.model.dto.SubCategoryDto;
import com.spring.server.model.entity.SubCategory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SubCategoryMapper {
    public static SubCategoryDto toDto(SubCategory category) {
        SubCategoryDto result = new SubCategoryDto();
        result.setId(category.getId());
        result.setTitle(category.getTitle());
        result.setSlug(category.getSlug());
        result.setIcon(category.getIcon());
        return result;
    }

    public static Set<SubCategoryDto> toDto(Set<SubCategory> categories) {
        Set<SubCategoryDto> list = new TreeSet<>();
        for (SubCategory category : categories)
            list.add(SubCategoryMapper.toDto(category));

        return list;
    }

    public static List<SubCategoryDto> toDto(List<SubCategory> categories) {
        List<SubCategoryDto> list = new ArrayList<>();
        for (SubCategory category : categories)
            list.add(SubCategoryMapper.toDto(category));

        return list;
    }

    public static SubCategory toEntity(SubCategoryDto category) {
        SubCategory result = new SubCategory();
        result.setId(category.getId());
        result.setTitle(category.getTitle());
        result.setSlug(category.getSlug());
        result.setIcon(category.getIcon());
        return result;
    }

    public static Set<SubCategory> toEntities(Set<SubCategoryDto> categories) {
        Set<SubCategory> list = new HashSet<>();
        for (SubCategoryDto category : categories) {
            list.add(SubCategoryMapper.toEntity(category));
        }
        return list;
    }

}
