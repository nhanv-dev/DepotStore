package com.spring.server.model.mapper;

import com.spring.server.model.dto.SalesRegisterDto;
import com.spring.server.model.entity.SalesRegister;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SalesRegisterMapper {

    public static SalesRegisterDto toDto(SalesRegister salesRegister) {
        SalesRegisterDto result = new SalesRegisterDto();
        result.setId(salesRegister.getId());
        result.setStatus(salesRegister.getStatus());
        result.setShopName(salesRegister.getShopName());
        result.setShopEmail(salesRegister.getShopEmail());
        result.setShopPhone(salesRegister.getShopPhone());
        result.setCity(salesRegister.getCity());
        result.setDistrict(salesRegister.getDistrict());
        result.setWards(salesRegister.getWards());
        result.setAddressDetail(salesRegister.getAddressDetail());
        result.setWarehouseRegionName(salesRegister.getWarehouseRegionName());
        result.setCreatedAt(salesRegister.getCreatedAt());
        result.setUpdatedAt(salesRegister.getUpdatedAt());
        result.setUser(UserMapper.toDto(salesRegister.getUser()));
        return result;
    }

    public static Page<SalesRegisterDto> toDto(Page<SalesRegister> salesRegisters) {
        return salesRegisters.map(new Function<SalesRegister, SalesRegisterDto>() {
            @Override
            public SalesRegisterDto apply(SalesRegister salesRegister) {
                return toDto(salesRegister);
            }
        });
    }

    public static SalesRegister toEntity(SalesRegisterDto salesRegister) {
        SalesRegister result = new SalesRegister();
        result.setId(salesRegister.getId());
        result.setStatus(salesRegister.getStatus());
        result.setShopName(salesRegister.getShopName());
        result.setShopEmail(salesRegister.getShopEmail());
        result.setShopPhone(salesRegister.getShopPhone());
        result.setCity(salesRegister.getCity());
        result.setDistrict(salesRegister.getDistrict());
        result.setWards(salesRegister.getWards());
        result.setAddressDetail(salesRegister.getAddressDetail());
        result.setWarehouseRegionName(salesRegister.getWarehouseRegionName());
        return result;
    }


}
