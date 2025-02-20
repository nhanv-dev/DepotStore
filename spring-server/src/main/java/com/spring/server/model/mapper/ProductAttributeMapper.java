package com.spring.server.model.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.spring.server.model.dto.ProductAttributeDto;
import com.spring.server.model.entity.ProductAttribute;

@Component
public class ProductAttributeMapper {
    public static ProductAttributeDto toDto(ProductAttribute attribute) {
        ProductAttributeDto result = new ProductAttributeDto();
        result.setId(attribute.getId());
        result.setName(attribute.getName());
        result.setOptions(new TreeSet<>(ProductAttributeOptionMapper.toDto(attribute.getOptions())));
        return result;
    }

    public static Set<ProductAttributeDto> toDto(Set<ProductAttribute> attributes) {
        if (attributes == null)
            return null;
        Set<ProductAttributeDto> list = new HashSet<>();
        for (ProductAttribute attribute : attributes) {
            list.add(ProductAttributeMapper.toDto(attribute));
        }
        return list;
    }

    public static ProductAttribute toEntity(ProductAttributeDto attribute) {
        ProductAttribute result = new ProductAttribute();
        result.setId(attribute.getId());
        result.setName(attribute.getName());
        result.setOptions(ProductAttributeOptionMapper.toEntities(attribute.getOptions(), result));
        return result;
    }

    public static Set<ProductAttribute> toEntities(Set<ProductAttributeDto> attributes) {
        if (attributes == null)
            return null;
        Set<ProductAttribute> list = new HashSet<>();
        for (ProductAttributeDto attribute : attributes) {
            list.add(ProductAttributeMapper.toEntity(attribute));
        }
        return list;
    }
}
