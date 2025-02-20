package com.spring.server.model.mapper;

import com.spring.server.model.dto.UserAddressDto;
import com.spring.server.model.entity.UserAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class UserAddressMapper {
    public static UserAddressDto toDto(UserAddress userAddress) {
        UserAddressDto result = new UserAddressDto();
        result.setId(userAddress.getId());
        result.setCustomerName(userAddress.getCustomerName());
        result.setEmail(userAddress.getEmail());
        result.setPhoneNumber(userAddress.getPhoneNumber());
        result.setAddressDetail(userAddress.getAddressDetail());
        result.setCity(userAddress.getCity());
        result.setDistrict(userAddress.getDistrict());
        result.setWards(userAddress.getWards());
        result.setIsDefault(userAddress.getIsDefault());
        result.setIsDeleted(userAddress.getIsDeleted());
        result.setCreatedAt(userAddress.getCreatedAt());
        result.setUpdatedAt(userAddress.getUpdatedAt());
        return result;
    }

    public static Set<UserAddressDto> toDto(List<UserAddress> userAddresses) {
        if (userAddresses == null || userAddresses.isEmpty()) return new TreeSet<>();
        Set<UserAddressDto> result = new TreeSet<>();
        for (UserAddress userAddress : userAddresses) result.add(toDto(userAddress));
        return result;
    }

    public static Set<UserAddressDto> toDto(Set<UserAddress> userAddresses) {
        if (userAddresses == null || userAddresses.isEmpty()) return new TreeSet<>();
        Set<UserAddressDto> result = new TreeSet<>();
        for (UserAddress userAddress : userAddresses) result.add(toDto(userAddress));
        return result;
    }

    public static UserAddress toEntity(UserAddressDto userAddress) {
        UserAddress result = new UserAddress();
        result.setId(userAddress.getId());
        result.setCustomerName(userAddress.getCustomerName());
        result.setEmail(userAddress.getEmail());
        result.setPhoneNumber(userAddress.getPhoneNumber());
        result.setAddressDetail(userAddress.getAddressDetail());
        result.setCity(userAddress.getCity());
        result.setDistrict(userAddress.getDistrict());
        result.setWards(userAddress.getWards());
        result.setIsDefault(userAddress.getIsDefault());
        result.setCreatedAt(userAddress.getCreatedAt());
        result.setUpdatedAt(userAddress.getUpdatedAt());
        return result;
    }
}
