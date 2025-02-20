package com.spring.server.model.mapper;

import com.spring.server.model.entity.*;
import com.spring.server.model.dto.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class ProductDetailMapper {
    public static ProductDto toDto(Product product) {
        ProductDto result = ProductMapper.toDto(product);

        result.setDescription(product.getDescription());
        result.setShortDescription(product.getShortDescription());
        result.setReturnPolicies(ReturnPolicyMapper.toDto(product.getReturnPolicies()));
        result.setAttributes(ProductAttributeMapper.toDto(product.getAttributes()));
        result.setVariants(ProductVariantMapper.toDto(product.getVariants()));

        return result;
    }

    public static Product toEntity(ProductDto product) {
        Product result = ProductMapper.toEntity(product);

        result.setDescription(product.getDescription());
        result.setShortDescription(product.getShortDescription());
        result.setReturnPolicies(ReturnPolicyMapper.toEntities(product.getReturnPolicies()));
        result.setDeal(DealMapper.toEntity(product.getDeal()));
        result.setAttributes(ProductAttributeMapper.toEntities(product.getAttributes()));

        if (product.getVariants() != null && !product.getVariants().isEmpty()) {
            Set<ProductVariant> variants = new HashSet<>();
            for (ProductVariantDto variantDto : product.getVariants()) {
                ProductVariant variant = new ProductVariant();
                variant.setDeal(DealMapper.toEntity(variantDto.getDeal()));
                variant.setAttributeHash(variantDto.getAttributeHash());
                variant.setQuantity(variantDto.getQuantity());
                Set<ProductAttributeOption> options = new HashSet<>();
                for (ProductAttributeOptionDto optionDto : variantDto.getOptions()) {
                    A:
                    for (ProductAttribute attribute : result.getAttributes()) {
                        for (ProductAttributeOption option : attribute.getOptions()) {
                            if (!Objects.equals(option.getName(), optionDto.getName())) continue;
                            if (!Objects.equals(option.getValue(), optionDto.getValue())) continue;
                            if (!Objects.equals(option.getImage(), optionDto.getImage())) continue;
                            options.add(option);
                            break A;
                        }
                    }
                }
                variant.setOptions(options);
                variants.add(variant);
            }
            result.setVariants(variants);
        }

        return result;
    }

}
