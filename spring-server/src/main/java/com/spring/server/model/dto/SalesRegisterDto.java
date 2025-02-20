package com.spring.server.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.server.model.constant.ESalesRegisterStatus;

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
public class SalesRegisterDto implements Comparable<SalesRegisterDto> {
    private Long id, userId;
    private ESalesRegisterStatus status;
    private String shopName;
    private String shopPhone;
    private String shopEmail;
    private String city, district, wards, addressDetail, warehouseRegionName;
    private UserDto user;
    private Date createdAt, updatedAt;

    @Override
    public int compareTo(SalesRegisterDto o) {
        return createdAt.compareTo(o.getCreatedAt());
    }
}
