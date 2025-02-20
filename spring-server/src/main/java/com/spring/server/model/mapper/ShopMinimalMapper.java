package com.spring.server.model.mapper;

import com.spring.server.model.dto.ShopDto;
import com.spring.server.model.entity.Shop;
import org.springframework.stereotype.Component;

@Component
public class ShopMinimalMapper {

    public static ShopDto toDto(Shop shop) {
        ShopDto result = new ShopDto();

        result.setId(shop.getId());
        result.setShopName(shop.getShopName());
        result.setSlug(shop.getSlug());
        result.setShopLogo(shop.getShopLogo());
        result.setProductTotal(shop.getProductTotal());
        result.setShopEmail(shop.getEmail());
        result.setShopPhone(shop.getPhoneNumber());
        result.setOfficial(shop.isOfficialShop());
        result.setWarehouseRegionName(shop.getWarehouseRegionName());
        result.setDeleted(shop.isDeleted());
        result.setRatingInfo(RatingInfoMapper.toDto(shop.getRatingInfo()));

        return result;
    }


}
