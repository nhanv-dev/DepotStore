package com.spring.server.model.entity;

import java.io.Serializable;

import com.spring.server.model.constant.EOrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "orders_status")
public class OrderStatus extends BaseEntity implements Serializable {
    @Enumerated(EnumType.STRING)
    @Column
    private EOrderStatus status;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String labelConfirm;
    @Column
    private String labelCreatedAt;

}
