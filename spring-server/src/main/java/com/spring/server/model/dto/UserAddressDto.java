package com.spring.server.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
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
public class UserAddressDto implements Comparable<UserAddressDto> {
    private Long id, userId;
    @NotBlank
    private String city, district, wards, addressDetail, customerName, phoneNumber, email;
    private Boolean isDefault, isDeleted;
    private Date createdAt, updatedAt;

    @Override
    public int compareTo(UserAddressDto o) {
        return createdAt.compareTo(o.getCreatedAt());
    }
}
