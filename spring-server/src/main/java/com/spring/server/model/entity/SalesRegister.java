package com.spring.server.model.entity;

import java.io.Serializable;

import com.spring.server.model.constant.ESalesRegisterStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales_register")
public class SalesRegister extends BaseEntity implements Serializable {
    @Column
    private ESalesRegisterStatus status;
    @Column
    private String shopName, shopEmail, shopPhone;
    @Column
    private String city, district, wards, addressDetail, warehouseRegionName;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;
}
