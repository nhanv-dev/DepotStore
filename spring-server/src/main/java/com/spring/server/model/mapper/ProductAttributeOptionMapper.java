package com.spring.server.model.mapper;

import com.spring.server.model.dto.ProductAttributeOptionDto;
import com.spring.server.model.entity.ProductAttribute;
import com.spring.server.model.entity.ProductAttributeOption;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Component
public class ProductAttributeOptionMapper {
    public static ProductAttributeOptionDto toDto(ProductAttributeOption option) {
        if (option == null) return null;
        ProductAttributeOptionDto result = new ProductAttributeOptionDto();
        result.setId(option.getId());
        if (option.getAttribute() != null)
            result.setAttributeId(option.getAttribute().getId());
        result.setName(option.getName());
        result.setValue(option.getValue());
        result.setImage(option.getImage());
        result.setDeleted(option.isDeleted());
        return result;
    }

    public static Set<ProductAttributeOptionDto> toDto(Set<ProductAttributeOption> options) {
        if (options == null || options.isEmpty()) return new TreeSet<>();
        Set<ProductAttributeOptionDto> list = new TreeSet<>();
        for (ProductAttributeOption option : options) {
            list.add(ProductAttributeOptionMapper.toDto(option));
        }
        return list;
    }

    public static ProductAttributeOption toEntity(ProductAttributeOptionDto option, ProductAttribute attribute) {
        ProductAttributeOption result = new ProductAttributeOption();
        result.setId(option.getId());
        result.setName(option.getName());
        result.setValue(option.getValue());
        result.setImage(option.getImage());
        result.setDeleted(option.isDeleted());
        result.setAttribute(attribute);
        return result;
    }


    public static ProductAttributeOption toEntity(ProductAttributeOptionDto option) {
        ProductAttributeOption result = new ProductAttributeOption();
        result.setId(option.getId());
        result.setName(option.getName());
        result.setValue(option.getValue());
        result.setImage(option.getImage());
        result.setDeleted(option.isDeleted());
        return result;
    }

    public static Set<ProductAttributeOption> toEntities(Set<ProductAttributeOptionDto> options, ProductAttribute attribute) {
        Set<ProductAttributeOption> result = new HashSet<>();
        for (ProductAttributeOptionDto option : options) {
            result.add(ProductAttributeOptionMapper.toEntity(option, attribute));
        }
        return result;
    }

    public static Set<ProductAttributeOption> toEntities(Set<ProductAttributeOptionDto> options) {
        Set<ProductAttributeOption> result = new HashSet<>();
        for (ProductAttributeOptionDto option : options) {
            result.add(ProductAttributeOptionMapper.toEntity(option));
        }
        return result;
    }
}


