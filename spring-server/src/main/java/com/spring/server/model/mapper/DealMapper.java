package com.spring.server.model.mapper;

import com.spring.server.model.dto.DealDto;
import com.spring.server.model.entity.Deal;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DealMapper {
    public static DealDto toDto(Deal deal) {
        if (deal == null) return null;
        DealDto result = new DealDto();
        result.setPrice(deal.getPrice());
        result.setFinalPrice(deal.getFinalPrice());
        result.setDiscountPercent(deal.getDiscountPercent());
        result.setId(deal.getId());
        return result;
    }

    public static Set<DealDto> toDto(Set<Deal> deals) {
        Set<DealDto> result = new HashSet<>();
        if (deals == null || deals.isEmpty()) return result;
        for (Deal deal : deals) {
            if (deal == null) continue;
            DealDto mapped = new DealDto();
            mapped.setId(deal.getId());
            mapped.setPrice(deal.getPrice());
            mapped.setFinalPrice(deal.getFinalPrice());
            mapped.setDiscountPercent(deal.getDiscountPercent());
            result.add(mapped);
        }
        return result;
    }

    public static Deal toEntity(DealDto deal) {
        if (deal == null) return null;
        Deal result = new Deal();
        result.setId(deal.getId());
        result.setPrice(deal.getPrice());
        result.setFinalPrice(deal.getFinalPrice());
        result.setDiscountPercent(deal.getDiscountPercent());
        return result;
    }


}
