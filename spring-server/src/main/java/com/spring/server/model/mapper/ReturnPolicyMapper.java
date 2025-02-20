package com.spring.server.model.mapper;

import com.spring.server.model.dto.ReturnPolicyDto;
import com.spring.server.model.entity.ReturnPolicy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class ReturnPolicyMapper {
    public static ReturnPolicyDto toDto(ReturnPolicy returnPolicy) {
        ReturnPolicyDto result = new ReturnPolicyDto();
        result.setId(returnPolicy.getId());
        result.setTitle(returnPolicy.getTitle());
        result.setTooltipTitle(result.getTooltipTitle());
        result.setTooltipContent(returnPolicy.getTooltipContent());
        return result;
    }

    public static Set<ReturnPolicyDto> toDto(Set<ReturnPolicy> returnPolicies) {
        if (returnPolicies == null) return null;
        Set<ReturnPolicyDto> result = new TreeSet<>();
        for (ReturnPolicy returnPolicy : returnPolicies) {
            result.add(ReturnPolicyMapper.toDto(returnPolicy));
        }
        return result;
    }

    public static Set<ReturnPolicyDto> toDto(List<ReturnPolicy> returnPolicies) {
        Set<ReturnPolicyDto> result = new TreeSet<>();
        for (ReturnPolicy returnPolicy : returnPolicies) {
            result.add(ReturnPolicyMapper.toDto(returnPolicy));
        }
        return result;
    }

    public static ReturnPolicy toEntity(ReturnPolicyDto returnPolicy) {
        ReturnPolicy result = new ReturnPolicy();
        result.setId(returnPolicy.getId());
        result.setTitle(returnPolicy.getTitle());
        result.setTooltipTitle(result.getTooltipTitle());
        result.setTooltipContent(returnPolicy.getTooltipContent());
        return result;
    }

    public static Set<ReturnPolicy> toEntities(Set<ReturnPolicyDto> returnPolicies) {
        if (returnPolicies == null) return null;
        Set<ReturnPolicy> result = new HashSet<>();
        for (ReturnPolicyDto returnPolicy : returnPolicies) {
            result.add(ReturnPolicyMapper.toEntity(returnPolicy));
        }
        return result;
    }
}
