package com.spring.server.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity()
@Table(name = "user_address")
@SQLDelete(sql = "update user_address u set u.is_deleted=true where u.id=:id")
public class UserAddress extends BaseEntity implements Serializable {
    @Column
    private String city, district, wards, addressDetail, customerName, phoneNumber, email;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted = false, isDefault = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "userAddress", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();
}
