package com.spring.server.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopDto {
    private Long id, userId;
    private String shopName;
    private String shopPhone;
    private String shopEmail;
    private String shopSlogan;
    private String shopLogo, shopBackground;
    private String city, district, wards, addressDetail, warehouseRegionName;
    private String slug;
    private String responseTime, timePrepareProduct;
    private boolean isDeleted, isOfficial;
    private Integer productTotal;
    private Integer orderCount;
    private RatingInfoDto ratingInfo;
    private Date createdAt, updatedAt;
}
