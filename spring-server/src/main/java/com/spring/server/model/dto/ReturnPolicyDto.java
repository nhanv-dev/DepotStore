package com.spring.server.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnPolicyDto implements Comparable<ReturnPolicyDto> {
    private Long id;
    private String title, tooltipTitle, tooltipContent;
    @Override
    public int compareTo(ReturnPolicyDto o) {
        return this.getId().compareTo(o.getId());
    }
}
